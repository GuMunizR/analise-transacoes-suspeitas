package br.com.gustavomr.analise.transacoes.suspeitas.controller;

import br.com.gustavomr.analise.transacoes.suspeitas.service.AnaliseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("analise")
public class AnaliseController {

    private AnaliseService analiseService;

    @GetMapping
    public String viewAnalise(){return "analise";}

    @PostMapping
    public ModelAndView analisarMes(@RequestParam String data){
        ModelAndView model = new ModelAndView("analise");
        Integer ano = Integer.parseInt(data.substring(0, data.indexOf("-")));
        Integer mes = Integer.parseInt(data.substring(data.indexOf("-")+1));
        model.addObject("transacoesSuspeitas", analiseService.analisarTransacao(ano, mes));
        model.addObject("contaSuspeitas", analiseService.analisarConta(ano, mes));
        model.addObject("agenciaSuspeitas", analiseService.analisarAgencia(ano, mes));
        return model;
    }
}
