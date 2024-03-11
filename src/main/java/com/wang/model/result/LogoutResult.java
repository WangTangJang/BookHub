package com.wang.model.result;

import lombok.Data;

@Data
public class LogoutResult {
    private int code;
    private String msg;
    private Data data;

    public static class Data {
        private int uid;
        private String token;

        public Data(int i, String s) {
            uid = i;
            token = s;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
