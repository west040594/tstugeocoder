package com.tstu.geocoder.controller;

import com.tstu.geocoder.model.Coordinates;
import com.tstu.geocoder.model.forms.AddressForm;
import com.tstu.geocoder.model.forms.CoordinatesForm;
import com.tstu.geocoder.service.SputnikService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@SessionAttributes("data")
public class MainController {

    private final SputnikService sputnikService;
    private final ModelMapper mapper;

    @ModelAttribute("data")
    public HashMap<Object, Object> getData() {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("coordinates", null);
        data.put("address", null);
        return data;
    }

    @GetMapping(value = {"/index", "/"})
    public String homePage(Model model) {
        model.addAttribute("addressForm", new AddressForm());
        model.addAttribute("coordinatesForm", new CoordinatesForm());
        return "index";
    }

    @PostMapping("/search/address")
    public String getCoordinatesByAddress(@ModelAttribute("addressForm") @Valid AddressForm addressForm,
                                          @ModelAttribute("data") HashMap<Object, Object> data,
                                          BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "index";
        } else {
            Coordinates coordinates = sputnikService.getCoordinatesByAddress(addressForm.getAddress());
            data.put("coordinates", coordinates);
            return "redirect:/index";
        }
    }


    @PostMapping("/search/coordinates")
    public String getAddressByCoordinates(@ModelAttribute("coordinatesForm") @Valid CoordinatesForm coordinatesForm,
                                          @ModelAttribute("data") HashMap<Object, Object> data,
                                          BindingResult result, Model model) {
        if(result.hasErrors()) {
            return "index";
        } else {
            String address = sputnikService.getAddressByCoordinates(mapper.map(coordinatesForm, Coordinates.class));
            data.put("address", address);
            return "redirect:/index";
        }
    }

}
