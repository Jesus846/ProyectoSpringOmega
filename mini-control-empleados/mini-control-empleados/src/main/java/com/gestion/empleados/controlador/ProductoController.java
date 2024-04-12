package com.gestion.empleados.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.gestion.empleados.models.ProductoModels;
import com.gestion.empleados.servicio.ProductoServicies;

import javax.swing.plaf.PanelUI;
import javax.websocket.server.PathParam;
import java.util.List;
@Controller
public class ProductoController {
    @Autowired
    private ProductoServicies productoServicies;

    @RequestMapping("/index")
    public String verpaginaDeInicio(Model model, @PathParam("palabraClae") String palabraClave) {
        Iterable<ProductoModels> productoModels = productoServicies.findAll(palabraClave);
        model.addAttribute("productoModels", productoModels);
        model.addAttribute("palabraClave", palabraClave);
        return "index";
    }

    @RequestMapping("/nuevo")
    public String mostrarFormularioDeRegistrarProducto(Model model) {
        ProductoModels producto = new ProductoModels();
        model.addAttribute("producto", producto);
        return "nuevo_producto";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String guardarProducto(@ModelAttribute("producto") ProductoModels producto) {
        productoServicies.save(producto);
        return "redirect:/index";
    }

    @RequestMapping("/editar/{id}")
    public ModelAndView mostrarFormularioDeEditarProducto(@PathVariable(name = "id") Long id) {
        ModelAndView modelo = new ModelAndView("editar_producto");
        ProductoModels producto = productoServicies.findById(id).get();
        modelo.addObject("producto", producto);
        return modelo;
    }

    @RequestMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoServicies.deleteById(id);
        return "redirect:/index";
    }

}
