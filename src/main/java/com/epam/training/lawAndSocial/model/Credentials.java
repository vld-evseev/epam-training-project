package com.epam.training.lawAndSocial.model;

import lombok.Value;

@Value
public class Credentials {

    String username;
    String password;

    @java.beans.ConstructorProperties({"username", "password"})
    Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static CredentialsBuilder builder() {
        return new CredentialsBuilder();
    }

    @Override
    public String toString() {
        return "Credentials{" +
                "username='" + username + '\'' +
                '}';
    }

    public static class CredentialsBuilder {
        private String username;
        private String password;

        CredentialsBuilder() {
        }

        public Credentials.CredentialsBuilder username(String username) {
            this.username = username;
            return this;
        }

        public Credentials.CredentialsBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Credentials build() {
            return new Credentials(username, password);
        }

        public String toString() {
            return "com.epam.training.lawAndSocial.model.Credentials.CredentialsBuilder(username=" + this.username + ", password=" + this.password + ")";
        }
    }
}
