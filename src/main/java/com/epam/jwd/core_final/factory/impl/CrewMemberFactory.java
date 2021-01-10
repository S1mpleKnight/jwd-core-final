package com.epam.jwd.core_final.factory.impl;

import com.epam.jwd.core_final.domain.CrewMember;
import com.epam.jwd.core_final.domain.Rank;
import com.epam.jwd.core_final.domain.Role;
import com.epam.jwd.core_final.exception.InvalidStateException;
import com.epam.jwd.core_final.factory.EntityFactory;

// do the same for other entities
public class CrewMemberFactory implements EntityFactory<CrewMember> {

    @Override
    public CrewMember create(Object... args) throws InvalidStateException{
        if (args == null){
            throw new InvalidStateException("null on CrewMemberFactory");
        } else if (args.length < 3){
            throw new InvalidStateException("null on CrewMemberFactory");
        } else {
            String name = (String) args[1];
            Role role = Role.resolveRoleById(Long.parseLong((String) args[0]));
            Rank rank = Rank.resolveRankById(Long.parseLong((String) args[2]));
            return new CrewMember(name, role, rank);
        }
    }
}
