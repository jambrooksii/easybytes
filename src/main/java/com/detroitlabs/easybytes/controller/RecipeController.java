package com.detroitlabs.easybytes.controller;

import com.detroitlabs.easybytes.data.RecipeRepository;
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

    @RequestMapping("/")
    public String displayHomepage(ModelMap modelMap){
        modelMap.put("allRecipes", recipeRepository.getAllRecipes());
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
    @RequestMapping("/singlerecipe/{region}")
    public String regionRecipe(@PathVariable String region, ModelMap modelMap){
        Recipe recipeRegion = recipeRepository.findbyRegion(region);
        modelMap.put("recipeRegion", recipeRegion);
        return "singlerecipe";
    }

}
