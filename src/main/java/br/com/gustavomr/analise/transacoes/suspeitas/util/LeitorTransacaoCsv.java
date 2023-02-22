package br.com.gustavomr.analise.transacoes.suspeitas.util;

import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeitorTransacaoCsv {

    public List<Transacao> lerTransacoes(InputStreamReader arquivo) {
        try {
            CSVReader reader = new CSVReaderBuilder(arquivo).build();
            List<String[]> linhas = reader.readAll();
            reader.close();
            List<Transacao> transacoes = new ArrayList<>();
            for(String[] linha : linhas) {
                List<String> lista = new ArrayList<>(Arrays.asList(linha));
                lista.removeIf(String::isBlank);
                try {
                   if(lista.size() == 8){
                       transacoes.add(new Transacao(lista.get(0), lista.get(1), lista.get(2), lista.get(3), lista.get(4),
                               lista.get(5), new BigDecimal(lista.get(6)), LocalDateTime.parse(lista.get(7))));
                   }
                } catch (NullPointerException | NumberFormatException | DateTimeException e) {

                }
            }
            return transacoes;
        } catch (IOException e) {
            return null;
        }
    }
}
