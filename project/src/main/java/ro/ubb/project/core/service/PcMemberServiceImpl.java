package ro.ubb.project.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.ubb.project.core.model.PcMember;
import ro.ubb.project.core.repository.PcMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PcMemberServiceImpl implements PcMemberService {

    @Autowired
    private PcMemberRepository pcMemberRepository;

    @Override
    public List<PcMember> getAllPcMembers() {
        return this.pcMemberRepository.findAll();
    }

    @Override
    public void addPcMember(PcMember pcMember) {
        this.pcMemberRepository.save(pcMember);
    }

    @Override
    public void deletePcMember(PcMember pcMember) {
        this.pcMemberRepository.delete(pcMember);
    }

    @Override
    public void updatePcMember(PcMember pcMember) {
        Optional<PcMember> toUpdate = this.pcMemberRepository.findById(pcMember.getPcid());
        if (toUpdate.isPresent()) {
            PcMember pm = toUpdate.get();
            this.pcMemberRepository.save(pm);
        } else {
            throw new RuntimeException("No assignment found");
        }
    }

    @Override
    public Optional<PcMember> getPcMemberById(int pcid) {
        return this.pcMemberRepository.findById(pcid);
    }

    public boolean isPcMember(int uid) {
        Optional<PcMember> member = this.pcMemberRepository.findAll()
                .stream()
                .filter(pcMember -> pcMember.getUid() == uid)
                .findAny();
        return member.isPresent();
    }

    @Override
    public int getPcIdByUid(int userId) {
        for(PcMember pc : pcMemberRepository.findAll()) {
            if (pc.getUid() == userId) {
                return pc.getPcid();
            }
        }
        throw new RuntimeException("no pc with this userId");
    }
}
