package com.example.WebRPG.controller;


import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Fortress;
import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import com.example.WebRPG.Service.Impl.dwarfImpl.GameDwarfServiceImpl;
import com.example.WebRPG.Service.MainHeroService;
import com.example.WebRPG.Service.dwarf.DwarfGroupService;
import com.example.WebRPG.Service.dwarf.FortressService;
import com.example.WebRPG.Service.dwarf.GameDwarfService;
import com.example.WebRPG.Service.dwarf.GateService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Controller
@RequestMapping("/dwarf_game")
public class DwarfGameController {
    @Autowired
    private final MainHeroService ms;
    @Autowired
    private final GameDwarfService gameDwarfService;
    @Autowired
    private final FortressService fs;
    @Autowired
    private final DwarfGroupService dwarfGroupService;
    @Autowired
    private final GateService gateService;

    private Random random = new Random();


    @GetMapping("/set_stats")
    public String getStats(HttpSession session, Model model) {

        model.addAttribute("new_fortress", new Fortress());
        return "fortress/new-fortress";
    }

    @PostMapping("/set_stats")
    public String setStats(@ModelAttribute Fortress fortress, HttpSession session, Model model) {
        Fortress saveFortress = fs.saveFortress(fortress);
        fs.addNewDwarf(fortress);
        fs.addNewDwarf(fortress);
        fs.addGatesToFortress(saveFortress.getId());

        session.setAttribute("fortress_id", saveFortress.getId());
        return "redirect:/dwarf_game/preparation";
    }

    @GetMapping("/preparation")
    public String preparation(HttpSession session, Model model) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        model.addAttribute("fortress", fortress);
        model.addAttribute("text", session.getAttribute("text"));
        return "fortress/preparation";
    }

    @GetMapping("/dwarfs_stats")
    public String dwarfsStats(HttpSession session, Model model) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        model.addAttribute("fortress", fortress);

        return "fortress/dwarfs-stats";
    }

    @PostMapping("/update_level_dwarf/{id}")
    public String updateLevelDwarf(@PathVariable("id") String id, HttpSession session, Model model) {
        DwarfGroup dwarf = dwarfGroupService.findById(id);
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);

        session.setAttribute("fortress_id", fortress.getId());
        model.addAttribute("fortress", fortress);
        session.setAttribute("text", "Не достаточно опыта");

        if (fortress.getExperience() > 0) {
            dwarf.levelUp();
            fortress.setExperience(fortress.getExperience() - 1);
            dwarfGroupService.saveDwarfGroup(dwarf);
            session.setAttribute("text", "Успешное улучшение");
        }

        return "redirect:/dwarf_game/preparation";
    }
    @GetMapping("/select_dwarf")
    public String selectDwarf(Model model, HttpSession session) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        List<DwarfGroup> dwarfGroups = fortress.getDwarfGroups();
        model.addAttribute("dwarfGroups", dwarfGroups);
        model.addAttribute("fortress", fortress);
        return "fortress/select-dwarf";
    }

    @PostMapping("/buying_Dwarfs")
    public String buyingDwarves(HttpSession session, Model model) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        session.setAttribute("text", "Не достаточно денег");

        if (fortress.getBalance() > 50) {
            fortress.setBalance(fortress.getBalance() - 50);
            fs.addNewDwarf(fortress);
            model.addAttribute("fortress", fortress);
            session.setAttribute("text", "Успешное улучшение");
        }
        return "redirect:/dwarf_game/preparation";
    }
    @PostMapping("/buying_provision")
    public String buyingProvision(HttpSession session, Model model) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        session.setAttribute("text", "Не достаточно опыта");
        if (fortress.getBalance() > 50) {
            fortress.setBalance(fortress.getBalance() - 100);
            fortress.setProvision(fortress.getProvision() + 1);
            fs.saveFortress(fortress);
            session.setAttribute("text", "Успешное улучшение");
        }
        model.addAttribute("fortress", fortress);
        return "redirect:/dwarf_game/preparation";
    }

    @GetMapping("/state_gates")
    public String stateGates(HttpSession session, Model model) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        model.addAttribute("fortress", fortress);

        return "fortress/state-gates";
    }
    @GetMapping("/select_gates")
    public String upgradeGates(HttpSession session, Model model) {
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);
        List<Gate> gates = fortress.getGates();
        model.addAttribute("gates", gates);
        model.addAttribute("fortress", fortress);
        return "fortress/select-gates";
    }


    @PostMapping("/upgrade_gates/{id}")
    public String upgradeGate(@PathVariable("id") String id, HttpSession session, Model model) {
        Gate gate = gateService.findById(id);
        String fortressId = (String) session.getAttribute("fortress_id");
        Fortress fortress = fs.findById(fortressId);

        model.addAttribute("fortress", fortress);
        session.setAttribute("text", "Не достаточно денег");

        if (fortress.getBalance() >= 100) {
            gate.setProtection(gate.getProtection() + 20);
            fortress.setBalance(fortress.getBalance() - 100);
            gateService.saveGateGroup(gate);
            session.setAttribute("text", "Успешное улучшение");
        } else if(gate.getProtection() >= 80) {
            session.setAttribute("text", "Достигнут лимит");

        }

        return "redirect:/dwarf_game/preparation";
    }


    @GetMapping("/exist")
    public String exist(HttpSession httpSession, Model model ) {
        return "set_name_form";
    }

    @GetMapping("/fortress_defense")
    public String fortressDefense(HttpSession session, Model model) {
        return "fortress/select-gate-in-defense";
    }


    @PostMapping("/fortress_defense/{id}")
    public String fortressDefense(@PathVariable("id") String id, HttpSession session, Model model) {

        return "redirect:/dwarf_game/select_gate_in_defense";
    }

    @GetMapping("/select_gate_in_defense")
    public String selectGateInDefense(HttpSession session, Model model) {
        return "fortress/select-gate-in-defense";
    }


    @GetMapping("/select_dwarfs_defense")
    public String selectDwarfsDefense(HttpSession session, Model model) {
        return "fortress/select-dwarfs-defense";
    }

    @PostMapping("/select_gate_in_defense/{id}")
    public String selectGateInDefense(@PathVariable("id") String id, HttpSession session, Model model) {

        return "redirect:/dwarf_game/fortress_defense";
    }
}
