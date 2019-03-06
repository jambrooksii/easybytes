package com.detroitlabs.easybytes.controller;

import com.detroitlabs.easybytes.data.RecipeRepository;
import com.detroitlabs.easybytes.data.RegionRepository;
import com.detroitlabs.easybytes.model.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private RegionRepository regionRepository;

    @RequestMapping("/")
    public String displayHomepage(ModelMap modelMap){
        modelMap.put("allRecipes", recipeRepository.getAllRecipes());

        modelMap.put("allRegions", regionRepository.getAll_Regions());

        return "index";
    }

    @RequestMapping("/addrecipe")
    public String addRecipe(){
        return "addrecipe";
    }

    @RequestMapping("/aboutus")
    public String aboutUs(){
        return "aboutus";
    }

    @RequestMapping("/search")
    public String recipeSearch(@RequestParam(name = "q") String searchValue, ModelMap modelMap){
        List<Recipe> searchResults = recipeRepository.searchForRecipe(searchValue);
        modelMap.put("searchResults", searchResults);
        return "search";
    }

    @RequestMapping("/singlerecipe/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap){
        Recipe singleRecipe = recipeRepository.findByName(name);
        modelMap.put("singleRecipe", singleRecipe);
        return "singlerecipe";
    }

}
