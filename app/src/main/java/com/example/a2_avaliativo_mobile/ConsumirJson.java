package com.example.a2_avaliativo_mobile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsumirJson {

    public static ArrayList<Cliente> jsonDados(String conteudo) {

        try {
            ArrayList<Cliente> clienteArrayList = new ArrayList<>();
            JSONArray jsonArray = null;
            JSONObject jsonObject = null;

            jsonArray = new JSONObject(conteudo).getJSONArray("data");


            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);

                Cliente cliente = new Cliente();
                cliente.setId(jsonObject.getString("id"));
                cliente.setFirstName(jsonObject.getString("first_name"));
                cliente.setLastName(jsonObject.getString("last_name"));
                cliente.setEmail(jsonObject.getString("email"));
                cliente.setAvatar(jsonObject.getString("avatar"));

                clienteArrayList.add(cliente);
            }

            return clienteArrayList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
