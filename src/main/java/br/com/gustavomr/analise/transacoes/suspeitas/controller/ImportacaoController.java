package br.com.gustavomr.analise.transacoes.suspeitas.controller;

import br.com.gustavomr.analise.transacoes.suspeitas.dto.DetalharDto;
import br.com.gustavomr.analise.transacoes.suspeitas.model.Importacao;
import br.com.gustavomr.analise.transacoes.suspeitas.service.ImportacaoService;
import br.com.gustavomr.analise.transacoes.suspeitas.service.TransacaoService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("/importar")
public class ImportacaoController {

    private TransacaoService transacaoService;
    private ImportacaoService importacaoService;

    @GetMapping
    public ModelAndView importacaoView(){
        ModelAndView model = new ModelAndView("importacao");
        return model.addObject("listImportacao", importacaoService.listarImportacao());
    }

    @PostMapping("salvar")
    public ModelAndView importarArquivo(@RequestParam("arquivo") MultipartFile arquivo) {
        ModelAndView model = new ModelAndView("redirect:/importar");
        String result = transacaoService.salvarTransacao(arquivo);
        if(result.substring(0, result.indexOf(":")).equals("err")){
            return model.addObject("errMensage", result.substring(result.indexOf(":")+1));
        }
        return model.addObject("scssMensage", result.substring(result.indexOf(":") + 1));
    }
    @GetMapping("detalhar")
    public ModelAndView detalharView(@RequestParam Long id){
        ModelAndView model = new ModelAndView("detalhar");
        Importacao importacao = importacaoService.buscarPorId(id);
        DetalharDto detalhar = new DetalharDto(importacao, transacaoService.listarTransacoesData(importacao.getDataTransacao()));
        return model.addObject("detalhar", detalhar);
    }

}
