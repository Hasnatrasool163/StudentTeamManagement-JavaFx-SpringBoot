package com.teamapp.server.repo;

import com.teamapp.server.model.TeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {
    List<TeamMember> findByStudentId(String studentId);
}
