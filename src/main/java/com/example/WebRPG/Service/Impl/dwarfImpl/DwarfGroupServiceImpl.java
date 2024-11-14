package com.example.WebRPG.Service.Impl.dwarfImpl;

import com.example.WebRPG.Model.Characters.Dwarf.DwarfGroup;
import com.example.WebRPG.Repositories.dwarfRepositories.DwarfGroupRepository;
import com.example.WebRPG.Service.dwarf.DwarfGroupService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Data
public class DwarfGroupServiceImpl implements DwarfGroupService {
    @Autowired
    private final DwarfGroupRepository dwarfGroupRepository;

    @Override
    public DwarfGroup findById(String id) {
        return dwarfGroupRepository.findById(id).orElse(null);
    }

    @Override
    public DwarfGroup saveDwarfGroup(DwarfGroup dwarfGroup) {
        return dwarfGroupRepository.save(dwarfGroup);
    }

    @Override
    public void deleteDwarfGroupById(String id) {
        dwarfGroupRepository.deleteById(id);
    }

    @Override
    public Optional<DwarfGroup> getDwarfById(String id) {
        return dwarfGroupRepository.findById(id);
    }
}
