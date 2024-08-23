package com.example.test_api_IBGE.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ResponseWrapper {

    private String id;
    private String variavel;
    private String unidade;
    private List<Resultado> resultados;

    @Getter
    @Setter
    public static class Resultado {
        private List<Object> classificacoes;
        private List<Serie> series;
    }

        @Getter
        @Setter
        public static class Serie {
            private Localidade localidade;
            private Map<String, String> serie;
        }

            @Getter
            @Setter
            public static class Localidade {
                private String id;
                private Nivel nivel;
                private String nome;
            }

                @Getter
                @Setter
                public static class Nivel {
                    private String id;
                    private String nome;
                }


}
