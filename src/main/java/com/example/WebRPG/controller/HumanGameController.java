package com.example.WebRPG.controller;

import com.example.WebRPG.Model.Characters.Person.Enemies;
import com.example.WebRPG.Model.Characters.Person.MainHero;
import com.example.WebRPG.Service.ArmorService;
import com.example.WebRPG.Service.GameService;
import com.example.WebRPG.Service.MainHeroService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("human_game")
public class HumanGameController {
    private final GameService gameService;
    private final MainHeroService ms;
    private final ArmorService as;

    @GetMapping("/human")
    public String beginning(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);
        if (mainHero.isEndGame()) {
            return "redirect:/game-over";
        }
        return "human";
    }

    @GetMapping("/battle")
    public String getBattle(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (session.getAttribute("enemies") == null) {
            Enemies rnEnemies = gameService.rnEnemies();
            session.setAttribute("enemies", rnEnemies);
            model.addAttribute("enemies", rnEnemies);
        } else {
            model.addAttribute("enemies", session.getAttribute("enemies"));
        }

        if (!mainHero.isEndGame()) {
            return "battle";
        }
        return "game-over";
    }

    @PostMapping("/attack")
    public String attack(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        Enemies enemies = (Enemies) session.getAttribute("enemies");

        gameService.attack(mainHero, enemies);
        ms.saveMainHero(mainHero);
        if (enemies.getHealth() <= 0) {
            mainHero.setMoney(mainHero.getMoney() + enemies.getMany());
            mainHero.setLevel(mainHero.getLevel() + 1);
            ms.saveMainHero(mainHero);

            Enemies rnEnemies = gameService.rnEnemies();
            session.setAttribute("enemies", rnEnemies);
        }

        if (mainHero.isEndGame()) {
            return "redirect:/game-over";
        }
        return "redirect:/human_game/battle";
    }

    @PostMapping("/usePotion")
    public String usePotion(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (gameService.usePotion(mainHero)) {
            model.addAttribute("text", "Вы использовали зелье");
        } else {
            model.addAttribute("text", "Не достаточно денег");
        }

        if (mainHero.isEndGame()) {
            return "redirect:/game-over";
        }
        return beginning(session, model);
    }

    @PostMapping("/upgradeWeapon")
    public String upgradeWeapon(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (gameService.upgradeWeapon(mainHero)) {
            model.addAttribute("text", "Вы успешно улучшили оружие");
        } else {
            model.addAttribute("text", "Не достаточно денег");
        }

        if (mainHero.isEndGame()) {
            return "redirect:/game-over";
        }
        return beginning(session, model);
    }

    @PostMapping("/upgradeArmor")
    public String upgradeArmor(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);

        if (mainHero.getMoney() >= 100) {
            model.addAttribute("text", "Вы улучшили броню!");
            mainHero.setMoney(mainHero.getMoney() - 100);
            ms.saveMainHero(mainHero);
            as.upgradeDefense(mainHero.getArmor().getId(), 5);

        } else {
            model.addAttribute("text", "Не достаточно денег");
        }
        model.addAttribute("main_hero", mainHero);

        if (mainHero.isEndGame()) {
            return "redirect:/game-over";
        }
        return beginning(session, model);
    }
}
