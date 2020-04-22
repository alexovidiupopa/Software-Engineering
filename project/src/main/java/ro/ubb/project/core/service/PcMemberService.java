package ro.ubb.project.core.service;

import ro.ubb.project.core.model.PcMember;

import java.util.List;

public interface PcMemberService {

    List<PcMember> getAllPcMembers();
    void addPcMember(PcMember pcMember);
    void deletePcMember(PcMember pcMember);
    void updatePcMember(PcMember pcMember);
}
