package com.example.WebRPG.Service.Impl.dwarfImpl;

import com.example.WebRPG.Model.Characters.MagicalBest.PC;
import com.example.WebRPG.Model.Item.MegicalBest.Abilities;
import com.example.WebRPG.Repositories.MegicalRepositories.PCRepository;
import com.example.WebRPG.Service.PcService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Random;

@RequiredArgsConstructor
@Data
@Service
public class PcServiceImpl implements PcService {
    @Autowired
    private PCRepository magicalRepository;

    public Random random;

    @Override
    public PC findById(String id) {
        return magicalRepository.findById(id).orElse(null);
    }
    @Override
    public PC savePC(PC pc) {
        return magicalRepository.save(pc);
    }
    @Override

    public void deletePCById(String id) {
        magicalRepository.deleteById(id);
    }

    public ArrayList<Abilities> getRandomAbilities(int count, ArrayList<Abilities> abilities) {
        ArrayList<Abilities> randomAbilities = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            randomAbilities.add(abilities.get(random.nextInt(abilities.size())));
        }
        return randomAbilities;
    }

    public void li(ArrayList<Abilities> abilities) {
        abilities = new ArrayList<>();
        random = new Random();
        abilities.add(new Abilities("Огненный Шар", 25.0, 5, false));
        abilities.add(new Abilities("Ледяной Осколок", 15.0, 3, false));
        abilities.add(new Abilities("Удар Молнии", 35.0, 7, false));
        abilities.add(new Abilities("Воздушный Разрез", 10.0, 2, false));
        abilities.add(new Abilities("Землетрясение", 50.0, 10, false));
        abilities.add(new Abilities("Водяной Поток", 18.0, 4, false));
        abilities.add(new Abilities("Темный Шип", 30.0, 6, false));
        abilities.add(new Abilities("Святой Свет", 20.0, 5, false));
        abilities.add(new Abilities("Арканный Импульс", 12.0, 3, false));
        abilities.add(new Abilities("Ядовитый Туман", 8.0, 2, false));
        abilities.add(new Abilities("Метеоритный Дождь", 45.0, 9, false));
        abilities.add(new Abilities("Громовой Удар", 15.0, 4, true));
        abilities.add(new Abilities("Земной Разлом", 20.0, 5, true));
    }
}
