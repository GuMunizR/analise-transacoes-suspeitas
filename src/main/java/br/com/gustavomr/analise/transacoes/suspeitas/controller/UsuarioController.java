package br.com.gustavomr.analise.transacoes.suspeitas.controller;

import br.com.gustavomr.analise.transacoes.suspeitas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@AllArgsConstructor
@Controller
@RequestMapping("usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;

    @GetMapping()
    public ModelAndView viewUsuario(){
        ModelAndView model = new ModelAndView("usuarios");
        return model.addObject("listaUsuario", usuarioService.listarUsuarios());
    }

    @GetMapping("editar")
    public ModelAndView viewEditarUsuario(@RequestParam Long id){
        ModelAndView model = new ModelAndView("editarUsuario");
        return model.addObject("usuario", usuarioService.buscarPorId(id));
    }

    @PostMapping("/editar/salvar")
    public ModelAndView salvarAlteracao(@RequestParam Long id,@RequestParam String nome, @RequestParam String email){
        ModelAndView model = new ModelAndView("redirect:/usuarios");
        usuarioService.alterar(id, nome, email);
        return model;
    }

    @GetMapping("excluir")
    public ModelAndView excluirUsuario(@RequestParam Long id){
        ModelAndView model = new ModelAndView("redirect:/usuarios");
        usuarioService.desativarUsuario(id);
        return model;
    }

}
