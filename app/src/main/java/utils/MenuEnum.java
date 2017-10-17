package utils;

/**
 * Created by fabiodocoutooliveira on 10/11/17.
 */

public enum MenuEnum {

   // http://default-environment-esd83pimcc.elasticbeanstalk.com/services/SenacrsWS
        URL("http://192.168.0.12:8080/TccWS/services/SenacrsWS"), // http://tcc.decoschmitt.cloudbees.net/services/SenacrsWS   |  http://10.0.2.2:8080/TccWS/services/SenacrsWS  | externo: 192.168.0.12:8080 ou 192.168.1.101:8080 ou 192.168.1.102:8080
        COMPLEMENTO("Complemento/Fórmula"),
        MAMADA("Leite materno");

        private String descricao;

        MenuEnum(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }

        public void setDescricao(String descricao) {
            this.descricao = descricao;
        }

}
