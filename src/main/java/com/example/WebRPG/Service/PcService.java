package com.example.WebRPG.Service;

import com.example.WebRPG.Model.Characters.MagicalBest.PC;
import com.example.WebRPG.Repositories.MegicalRepositories.PCRepository;

public interface PcService {
    public PC findById(String id) ;
    public PC savePC(PC pc);
    public void deletePCById(String id);
}
