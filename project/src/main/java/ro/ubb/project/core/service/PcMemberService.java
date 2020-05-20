package ro.ubb.project.core.service;

import ro.ubb.project.core.model.PcMember;

import java.util.List;
import java.util.Optional;

public interface PcMemberService {

    List<PcMember> getAllPcMembers();

    void addPcMember(PcMember pcMember);

    void deletePcMember(PcMember pcMember);

    void updatePcMember(PcMember pcMember);

    Optional<PcMember> getPcMemberById(int pcid);

    boolean isPcMember(int uid);

    int getPcIdByUid(int userId);
}
