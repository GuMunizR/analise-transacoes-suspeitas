package br.com.gustavomr.analise.transacoes.suspeitas.util;

import br.com.gustavomr.analise.transacoes.suspeitas.dto.DestinoDto;
import br.com.gustavomr.analise.transacoes.suspeitas.dto.OrigemDto;
import br.com.gustavomr.analise.transacoes.suspeitas.dto.TransacaoDto;
import br.com.gustavomr.analise.transacoes.suspeitas.model.Transacao;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class LeitorTransacaoXml {

    public List<Transacao> lerTransacoes(InputStreamReader arquivo) {
        XStream stream = new XStream();
        stream.addPermission(NoTypePermission.NONE);
        stream.addPermission(NullPermission.NULL);
        stream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        stream.allowTypesByWildcard(new String[]{
                "br.com.gustavomr.analise.transacoes.suspeitas",
        });
        stream.processAnnotations(new Class[]{TransacaoDto.class, DestinoDto.class, OrigemDto.class});
        stream.alias("transacoes", List.class);

        stream.allowTypes(new Class[]{
                java.util.List.class,
                TransacaoDto.class
        });
        List<TransacaoDto> transacoesDto = (ArrayList<TransacaoDto>) stream.fromXML(arquivo);
        return  transacoesDto.stream()
                .map(transacaoDto -> transacaoDto.getTransacao())
                .filter(transacaoDto ->  transacaoDto != null)
                .collect(Collectors.toList());
    }
}