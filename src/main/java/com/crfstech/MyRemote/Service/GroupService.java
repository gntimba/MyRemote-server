package com.crfstech.MyRemote.Service;

import com.crfstech.MyRemote.DTO.GroupDTO;
import com.crfstech.MyRemote.DTO.MemberDTO;
import com.crfstech.MyRemote.Exception.GroupNotFound;
import com.crfstech.MyRemote.Exception.MemberNotFound;
import com.crfstech.MyRemote.Exception.NotFoundException;
import com.crfstech.MyRemote.Exception.UserErrors;
import com.crfstech.MyRemote.persistence.Dao.GroupDao;
import com.crfstech.MyRemote.persistence.Dao.MembersDao;
import com.crfstech.MyRemote.persistence.entity.User;
import com.crfstech.MyRemote.persistence.entity.group.Group;
import com.crfstech.MyRemote.persistence.entity.group.Member;
import com.crfstech.MyRemote.persistence.entity.group.SendMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {


    @Autowired
    private MembersDao membersDao;

    @Autowired
    private GroupDao groupDao;

    @Autowired
    private UserService userService;

    public Group createGroup(GroupDTO group) {
        User user = userService.getAuthenticatedUser().orElseThrow(NotFoundException::new);
        Group newGroup = new Group();
        if (groupDao.findByNameAndUserId(group.getName(), user.getId()).isPresent())
            throw new UserErrors("Group with name " + group.getName() + " already exists");
        newGroup.setName(group.getName());
        newGroup.setDescription(group.getDescription());

        newGroup.setUserId(user.getId());
        return groupDao.save(newGroup);
    }

    public Member createMember(MemberDTO member) {

        SendMethod sendMethod = SendMethod.valueOf(member.getSendMethod());
        Member newMember = new Member();
        newMember.setName(member.getName());
        newMember.setPhone(member.getPhone());
        if (!member.getEmail().isEmpty())
            newMember.setEmail(member.getEmail());
        if (!member.getWhatsappNo().isEmpty())
            newMember.setWhatsappNo(member.getWhatsappNo());
        User user = userService.getAuthenticatedUser().orElseThrow(NotFoundException::new);
        newMember.setUserId(user.getId());
        newMember.setSendMethod(sendMethod);
        String fullName = member.getName() + " " + member.getPhone();
        newMember.setCreatedBy(fullName);
        newMember.setModifiedBy(fullName);
        return membersDao.save(newMember);

    }

    public List<Group> getGroups() {
        User user = userService.getAuthenticatedUser().orElseThrow(NotFoundException::new);
        return groupDao.findByuserId(user.getId());
    }

    public List<Member> getMembers() {
        User user = userService.getAuthenticatedUser().orElseThrow(NotFoundException::new);
        return membersDao.findByuserId(user.getId());
    }

    public Group getGroup(String groupId) {
        return groupDao.findById(groupId).orElseThrow(GroupNotFound::new);
    }

    public Member getMember(String memberId) {
        return membersDao.findById(memberId).orElseThrow(MemberNotFound::new);
    }

    public Group assigntoGroup(String groupId, String memberId) {
        Group group = groupDao.findById(groupId).orElseThrow(GroupNotFound::new);
        Member member = membersDao.findById(memberId).orElseThrow(MemberNotFound::new);
        group.getMembers().add(member);
        try {
            return groupDao.save(group);
        } catch (Exception e) {
            throw new UserErrors("Member already assigned to group");
        }

    }

}
