package com.crfstech.MyRemote.Controller;


import com.crfstech.MyRemote.DTO.AssignGroupDTO;
import com.crfstech.MyRemote.DTO.GroupDTO;
import com.crfstech.MyRemote.DTO.MemberDTO;
import com.crfstech.MyRemote.Service.GroupService;
import com.crfstech.MyRemote.persistence.entity.Device.UserDevices;
import com.crfstech.MyRemote.persistence.entity.group.Group;
import com.crfstech.MyRemote.persistence.entity.group.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("group")
@CrossOrigin
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/findMember/{id}")
    public Member getMember(@PathVariable String id) {
        return groupService.getMember(id);
    }

    @GetMapping("/findGroup/{id}")
    public Group getGroup(@PathVariable String id) {
        return groupService.getGroup(id);
    }

    @GetMapping("/findGroups")
    public List<Group> getGroup() {
        return groupService.getGroups();
    }

    @GetMapping("/findMembers")
    public List<Member> getMembers() {
        return groupService.getMembers();
    }

    @PostMapping("/assign")
    public Group Assign(@RequestBody AssignGroupDTO d) {
        return groupService.assigntoGroup(d.getGroupId(), d.getMemberId());
    }

    @PostMapping("/createGroup")
    public Group Assign(@RequestBody GroupDTO d) {
        return groupService.createGroup(d);
    }

    @PostMapping("/CreateMember")
    public Member Assign(@RequestBody MemberDTO d) {
        return groupService.createMember(d);
    }


}
