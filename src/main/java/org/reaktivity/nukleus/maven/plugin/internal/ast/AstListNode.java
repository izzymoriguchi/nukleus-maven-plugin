/**
 * Copyright 2016-2019 The Reaktivity Project
 *
 * The Reaktivity Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.reaktivity.nukleus.maven.plugin.internal.ast;

import static java.util.Collections.unmodifiableList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class AstListNode extends AstNamedNode
{
    private final List<AstListMemberNode> members;
    private final AstType templateType;
    private final AstType lengthType;
    private final AstType fieldCountType;
    private final Byte missingFieldByte;

    public List<AstListMemberNode> members()
    {
        return members;
    }

    public AstType templateType()
    {
        return templateType;
    }

    public AstType lengthType()
    {
        return lengthType;
    }

    public AstType fieldCountType()
    {
        return fieldCountType;
    }

    public Byte missingFieldByte()
    {
        return missingFieldByte;
    }

    @Override
    public AstNamedNode withName(
        String name)
    {
        return new AstListNode(name, members, templateType, lengthType, fieldCountType, missingFieldByte);
    }

    @Override
    public Kind getKind()
    {
        return Kind.LIST;
    }

    @Override
    public <R> R accept(
        Visitor<R> visitor)
    {
        return visitor.visitList(this);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(name, members, templateType, lengthType, fieldCountType);
    }

    @Override
    public boolean equals(
        Object o)
    {
        if (o == this)
        {
            return true;
        }

        if (!(o instanceof AstListNode))
        {
            return false;
        }

        AstListNode that = (AstListNode) o;
        return Objects.equals(this.name, that.name) &&
            Objects.equals(this.members, that.members) &&
            Objects.equals(this.templateType, that.templateType) &&
            Objects.equals(this.lengthType, that.lengthType) &&
            Objects.equals(this.fieldCountType, that.fieldCountType);
    }

    private AstListNode(
        String name,
        List<AstListMemberNode> members,
        AstType templateType,
        AstType lengthType,
        AstType fieldCountType,
        Byte missingFieldByte)
    {
        super(name);
        this.members = unmodifiableList(members);
        this.templateType = templateType;
        this.lengthType = lengthType;
        this.fieldCountType = fieldCountType;
        this.missingFieldByte = missingFieldByte;
    }

    public static final class Builder extends AstNamedNode.Builder<AstListNode>
    {
        private List<AstListMemberNode> members;
        private AstType templateType;
        private AstType lengthType;
        private AstType fieldCountType;
        private Byte missingFieldByte;

        public Builder()
        {
            this.members = new LinkedList<>();
        }

        public Builder name(
            String name)
        {
            this.name = name;
            return this;
        }

        public Builder member(
            AstListMemberNode member)
        {
            this.members.add(member);
            return this;
        }

        public Builder templateType(
            AstType templateType)
        {
            this.templateType = templateType;
            return this;
        }

        public Builder lengthType(
            AstType lengthType)
        {
            this.lengthType = lengthType;
            return this;
        }

        public Builder fieldCountType(
            AstType fieldCountType)
        {
            this.fieldCountType = fieldCountType;
            return this;
        }

        public Builder missingFieldByte(
            Byte missingFieldByte)
        {
            this.missingFieldByte = missingFieldByte;
            return this;
        }

        @Override
        public AstListNode build()
        {
            return new AstListNode(name, members, templateType, lengthType, fieldCountType, missingFieldByte);
        }
    }
}