package com.vone.vone.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.klaytn.caver.Caver;
import com.klaytn.caver.abi.datatypes.Type;
import com.klaytn.caver.contract.Contract;

import com.vone.vone.data.dao.HoldersVCDAO;
import com.vone.vone.data.dao.PostDAO;
import com.vone.vone.data.dao.SubmittedVCDAO;
import com.vone.vone.data.dao.VCDAO;
import com.vone.vone.data.dto.CredentialSubject;
import com.vone.vone.data.entity.VC;
import com.vone.vone.service.KlaytnService;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;
import org.web3j.protocol.http.HttpService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Example code about "How to execute Smart Contract."
 * Related reference - Korean: https://ko.docs.klaytn.com/bapp/sdk/caver-java/getting-started#smart-contract
 * Related reference - English: https://docs.klaytn.com/bapp/sdk/caver-java/getting-started#smart-contract
 */
@Service
public class KlaytnServiceImpl implements KlaytnService {
    // You can directly input values for the variables below, or you can enter values in the caver-java-examples/.env file.
    private static String nodeApiUrl = ""; // e.g. "https://node-api.klaytnapi.com/v1/klaytn";
    private static String accessKeyId = ""; // e.g. "KASK1LVNO498YT6KJQFUPY8S";
    private static String secretAccessKey = ""; // e.g. "aP/reVYHXqjw3EtQrMuJP4A3/hOb69TjnBT3ePKG";
    private static String chainId = ""; // e.g. "1001" or "8217";
    private static String contractAddress = "";
    private final VCDAO vcDAO;
    private static String salt = "";

    @Autowired
    public KlaytnServiceImpl(VCDAO vcDAO){
        this.vcDAO = vcDAO;
    }

    public static String objectToString(Object value) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(value);
    }

    public static void loadEnv() {
        Dotenv env;
        env = Dotenv.configure().directory(".").load();

        nodeApiUrl = nodeApiUrl.equals("") ? env.get("NODE_API_URL") : nodeApiUrl;
        accessKeyId = accessKeyId.equals("") ? env.get("ACCESS_KEY_ID") : accessKeyId;
        secretAccessKey = secretAccessKey.equals("") ? env.get("SECRET_ACCESS_KEY") : secretAccessKey;
        chainId = chainId.equals("") ? env.get("CHAIN_ID") : chainId;
        contractAddress = contractAddress.equals("") ? env.get("CONTRACT_ADDRESS") : contractAddress;
        salt = salt.equals("") ? env.get("SALT") : salt;
    }
    public String getVCFromKlaytn(String didId, Long vcId) throws Exception {
        loadEnv();
        HttpService httpService = new HttpService(nodeApiUrl);
        httpService.addHeader("Authorization", Credentials.basic(accessKeyId, secretAccessKey, StandardCharsets.UTF_8));
        httpService.addHeader("x-chain-id", chainId);
        Caver caver = new Caver(httpService);
        // abi is extracted by compiling caver-java-examples/resources/KVstore.sol using solc(solidity compiler)
        String abi = "[\n" +
                "{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"did\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"vcId\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"getVCById\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"components\": [\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"name\": \"id\",\n" +
                "\t\t\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\"name\": \"hashed\",\n" +
                "\t\t\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t\t\t}\n" +
                "\t\t\t\t],\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"tuple\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t}"+
                "]";
        // You can get contract address
        // by running caver-java-examples/contract/deploy scenario.;
        Contract contract = caver.contract.create(abi, contractAddress);
        List<Type> callResult = contract.call("getVCById", didId,vcId); //"did:vone:01E5B053B7ba8A91Bdb8FedD5814296c41aD522E"
        String res = objectToString(callResult.get(0));
        JSONObject jObject = new JSONObject(res);
        String response = (jObject.getJSONArray("value")).getJSONObject(1).getString("value");
        return response;
    }
    @Override
    public boolean verify(Long vcId) throws Exception{
        VC vc = vcDAO.selectVC(vcId);
        String didId = "did:vone:"+vc.getIssuer().substring(2);

        String hashOnChain = getVCFromKlaytn(didId, vcId);
        String hash = hash(vc.getContextValues());
        if(hash.equals(hashOnChain)){
            return true;
        }
        return false;
    }

    @Override
    public String hash(List<String> values) throws Exception {
        loadEnv();
        HttpService httpService = new HttpService(nodeApiUrl);
        httpService.addHeader("Authorization", Credentials.basic(accessKeyId, secretAccessKey, StandardCharsets.UTF_8));
        httpService.addHeader("x-chain-id", chainId);
        Caver caver = new Caver(httpService);
        // abi is extracted by compiling caver-java-examples/resources/KVstore.sol using solc(solidity compiler)
        String abi = "[\n" +
                "{\n" +
                "\t\t\"constant\": true,\n" +
                "\t\t\"inputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value1\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value2\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value3\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value4\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value5\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value6\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value7\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"_value8\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t},\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"salt\",\n" +
                "\t\t\t\t\"type\": \"string\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"name\": \"hash\",\n" +
                "\t\t\"outputs\": [\n" +
                "\t\t\t{\n" +
                "\t\t\t\t\"name\": \"\",\n" +
                "\t\t\t\t\"type\": \"bytes32\"\n" +
                "\t\t\t}\n" +
                "\t\t],\n" +
                "\t\t\"payable\": false,\n" +
                "\t\t\"stateMutability\": \"view\",\n" +
                "\t\t\"type\": \"function\"\n" +
                "\t}"+
                "]";
        // You can get contract address
        // by running caver-java-examples/contract/deploy scenario.
        Contract contract = caver.contract.create(abi, contractAddress);
        List<Type> callResult = contract.call("hash", values.get(0), values.get(1), values.get(2), values.get(3), values.get(4), values.get(5), values.get(6), values.get(7), salt);

        String res = objectToString(callResult.get(0));
        System.out.println(callResult);
        JSONObject jObject = new JSONObject(res);
        String result = jObject.getString("value");

        return result;
    }
}
