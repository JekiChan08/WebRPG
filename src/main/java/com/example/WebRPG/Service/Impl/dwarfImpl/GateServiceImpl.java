package com.example.WebRPG.Service.Impl.dwarfImpl;

import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Model.Characters.Dwarf.Gate;
import com.example.WebRPG.Repositories.dwarfRepositories.GateRepository;
import com.example.WebRPG.Service.dwarf.GateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateServiceImpl implements GateService {

    @Autowired
    private GateRepository gateRepository;
    @Override
    public Gate findById(String id) {
        return gateRepository.findById(id).orElse(null);
    }
    @Override
    public Gate saveGateGroup(Gate gate) {
        return gateRepository.save(gate);
    }

    @Override
    public void deleteGateById(String id) {
        gateRepository.deleteById(id);
    }

    @Override
    public boolean addInDwarf(DwarfGroup dwarfGroup, Gate gate) {
        return false;
    }


}
