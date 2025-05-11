package com.teamapp.server.controller;


import com.teamapp.server.model.TeamMember;
import com.teamapp.server.repo.TeamMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/members")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class TeamMemberController {

    @Autowired
    private TeamMemberRepository repo;

    @PostMapping
    public TeamMember addMember(@RequestBody TeamMember member) {
        return repo.save(member);
    }

    @GetMapping("/{id}")
    public Optional<TeamMember> getById(@PathVariable Long id) {
        return repo.findById(id);
    }

    @GetMapping
    public List<TeamMember> getAll() {
        return repo.findAll();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<TeamMember> getByStudentId(@PathVariable String studentId) {
        return repo.findByStudentId(studentId)
                .stream()
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public TeamMember updateMember(@PathVariable Long id, @RequestBody TeamMember updatedMember) {
        Optional<TeamMember> existingMemberOpt = repo.findById(id);
        if (existingMemberOpt.isPresent()) {
            TeamMember existingMember = existingMemberOpt.get();
            existingMember.setName(updatedMember.getName());
            existingMember.setStudentId(updatedMember.getStudentId());
            return repo.save(existingMember);
        } else {
            return null;
        }
    }


    @DeleteMapping("/delete/{id}")
    public void deleteMember(@PathVariable Long id) {
        System.out.println("delete team member with id: " + id);
        repo.deleteById(id);
    }
}
