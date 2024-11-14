package com.example.WebRPG.controller;

import com.example.WebRPG.Model.Characters.Person.Boss;
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
@RequestMapping("/dwarf_game")
public class DwarfGameController {
    private final GameService gameService;
    private final MainHeroService ms;
    private final ArmorService as;
    private final String end = "Поздравляю вас с победой над главным злодеем! Ваши деяния будут прославлены среди гномов как король пивных бочек). ";

    @GetMapping("/dwarf")
    public String beginning(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);
        model.addAttribute("end", end);

        if (mainHero.isEndGame()) {
            return "redirect:/dwarf_game/game_over";
        }
        return "dwarf";
    }

    @GetMapping("/battle")
    public String getBattle(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (session.getAttribute("enemies") == null) {
            Enemies rnEnemies = gameService.dwarfEnemies(mainHero.getLevel());
            session.setAttribute("enemies", rnEnemies);
            model.addAttribute("enemies", rnEnemies);
        } else {
            model.addAttribute("enemies", session.getAttribute("enemies"));
        }
        model.addAttribute("end", end);

        if (mainHero.getLevel() >= 4) {
            return "redirect:/dwarf_game/boss_battle";
        } else if (!mainHero.isEndGame()) {
            return "battle-dwarf";
        }
        return "redirect:/dwarf_game/game_over";
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

            Enemies rnEnemies = gameService.dwarfEnemies(mainHero.getLevel());
            session.setAttribute("enemies", rnEnemies);
        }
        model.addAttribute("end", end);

        if (mainHero.isEndGame()) {
            return "redirect:/dwarf_game/game_over";
        }
        return "redirect:/dwarf_game/battle";
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
        model.addAttribute("end", end);

        if (mainHero.isEndGame()) {
            return "redirect:/dwarf_game/game_over";
        }
        return beginning(session, model);
    }

    @PostMapping("/use_potion_in_battle")
    public String usePotionInBattle(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (gameService.usePotion(mainHero)) {
            model.addAttribute("text", "Вы использовали зелье");
        } else {
            model.addAttribute("text", "Не достаточно денег");
        }
        model.addAttribute("end", end);

        if (mainHero.isEndGame()) {
            return "redirect:/dwarf_game/game_over";
        }
        return "redirect:/dwarf_game/battle";
    }

    @GetMapping("/boss_battle")
    public String battleBoss(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (session.getAttribute("boss") == null) {
            Boss boss = gameService.rnBoss(mainHero.getLevel());
            session.setAttribute("boss", boss);
            model.addAttribute("boss", boss);
        } else {
            model.addAttribute("boss", session.getAttribute("boss"));
        }
        model.addAttribute("end", end);

        if (!mainHero.isEndGame()) {
            return "boss-battle-dwarf";
        }
        return "redirect:/dwarf_game/game_over";
    }
    @PostMapping("/attack_boss")
    public String attackBoss(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        Boss boss = (Boss) session.getAttribute("boss");

        gameService.attackBoss(mainHero, boss);
        ms.saveMainHero(mainHero);
        session.setAttribute("boss", boss);
        model.addAttribute("end", end);

        if (boss.getHealth() <= 0) {
            model.addAttribute("end", end);
            return "redirect:/dwarf_game/game_over";
        }

        if (mainHero.isEndGame()) {
            return "redirect:/dwarf_game/game_over";
        }

        return "redirect:/dwarf_game/boss_battle";
    }

    @GetMapping("/game_over")
    public String gameOver(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);
        model.addAttribute("boss", session.getAttribute("boss"));
        model.addAttribute("end", end);
        return "game-over";
    }
}
