package br.com.gustavomr.analise.transacoes.suspeitas.controller;

import br.com.gustavomr.analise.transacoes.suspeitas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cadastrar")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public String cadastro(){
        return "cadastro";
    }

    @PostMapping()
    public ModelAndView cadastrar(@RequestParam  String nome,@RequestParam String email){
        String  resultado = usuarioService.cadastrarUsuario(nome, email);
        if(resultado.substring(0, resultado.indexOf(":")).equals("err")){
            ModelAndView model = new ModelAndView("cadastro");
            System.out.println("entrou");
            return model.addObject("msgError", resultado.substring(resultado.indexOf(":")+1));
        }
        ModelAndView model = new ModelAndView("login");
        return model.addObject("msgCadastro", "Sua senha para se logar foi enviada no email");
    }
}
