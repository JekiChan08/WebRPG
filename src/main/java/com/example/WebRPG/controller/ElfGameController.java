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
@RequestMapping("/elf_game")
public class ElfGameController {
    private final GameService gameService;
    private final MainHeroService ms;
    private final ArmorService as;
    private final String end = "Поздравляю вас с победой над главным злодеем этого мира весь мир благодарна вам за то что вы смогли защитить их земли и семьи. Ваша победа навсегда останется в истории как спаситель всего мира.";

    @GetMapping("/elf")
    public String beginning(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);
        model.addAttribute("end", end);


        if (mainHero.isEndGame()) {
            return "redirect:/elf_game/game_over";
        }
        return "elf";
    }

    @GetMapping("/battle")
    public String getBattle(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);

        if (session.getAttribute("enemies") == null) {
            Enemies rnEnemies = gameService.elfEnemies(mainHero.getLevel());
            session.setAttribute("enemies", rnEnemies);
            model.addAttribute("enemies", rnEnemies);
        } else {
            model.addAttribute("enemies", session.getAttribute("enemies"));
        }
        model.addAttribute("end", end);

        model.addAttribute("main_hero", mainHero);
        if (mainHero.getLevel() >= 4) {
            return "redirect:/elf_game/boss_battle";
        }else if (!mainHero.isEndGame()) {
            return "battle-elf";
        }
        return "redirect:/elf_game/game_over";
    }

    @PostMapping("/attack")
    public String attack(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        Enemies enemies = (Enemies) session.getAttribute("enemies");
        model.addAttribute("end", end);

        gameService.attack(mainHero, enemies);
        ms.saveMainHero(mainHero);
        if (enemies.getHealth() <= 0) {
            mainHero.setMoney(mainHero.getMoney() + enemies.getMany());
            mainHero.setLevel(mainHero.getLevel() + 1);
            ms.saveMainHero(mainHero);

            Enemies rnEnemies = gameService.elfEnemies(mainHero.getLevel());
            session.setAttribute("enemies", rnEnemies);
        }
        model.addAttribute("end", end);

        if (mainHero.isEndGame()) {
            return "redirect:/elf_game/game_over";
        }
        return "redirect:/elf_game/battle";
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
            return "redirect:/elf_game/game_over";
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
            return "redirect:/elf_game/game_over";
        }
        return "redirect:/elf_game/battle";
    }
    @PostMapping("/use_potion_in_boss_battle")
    public String usePotionInBossBattle(HttpSession session, Model model) {
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
            return "redirect:/elf_game/game_over";
        }
        return "redirect:/elf_game/boss_battle";
    }


    @PostMapping("/upgradeWeapon")
    public String upgradeWeapon(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);
        model.addAttribute("end", end);

        if (gameService.upgradeWeapon(mainHero)) {
            model.addAttribute("text", "Вы успешно улучшили оружие");
        } else {
            model.addAttribute("text", "Не достаточно денег");
        }

        if (mainHero.isEndGame()) {
            return "redirect:/elf_game/game_over";
        }
        return beginning(session, model);
    }

    @PostMapping("/upgradeArmor")
    public String upgradeArmor(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("end", end);

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
            return "redirect:/elf_game/game_over";
        }
        return beginning(session, model);
    }
    @GetMapping("/boss_battle")
    public String battleBoss(HttpSession session, Model model) {
        String heroId = (String) session.getAttribute("hero_id");
        MainHero mainHero = ms.findById(heroId);
        model.addAttribute("main_hero", mainHero);
        model.addAttribute("end", end);

        if (session.getAttribute("boss") == null) {
            Boss boss = gameService.rnBoss(mainHero.getLevel());
            session.setAttribute("boss", boss);
            model.addAttribute("boss", boss);
        } else {
            model.addAttribute("boss", session.getAttribute("boss"));
        }

        if (!mainHero.isEndGame()) {
            return "boss-battle-elf";
        }
        return "redirect:/elf_game/game_over";
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
            return "redirect:/elf_game/game_over";
        }

        if (mainHero.isEndGame()) {
            return "redirect:/elf_game/game_over";
        }

        return "redirect:/elf_game/boss_battle";
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
