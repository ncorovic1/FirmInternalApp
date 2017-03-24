package com.FirmInternalApp.TeamsModule.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.FirmInternalApp.TeamsModule.models.*;

public interface TeamRepository extends JpaRepository<Team, Long> {}