package tokyo.peya.plugin.javasm.jvm;

import lombok.Getter;

@Getter
public class AccessAttributeSet
{
    private final AccessAttribute[] attributes;

    public AccessAttributeSet(AccessAttribute... attributes)
    {
        this.attributes = attributes;
    }

    public AccessAttributeSet(String... attributeNames)
    {
        this.attributes = new AccessAttribute[attributeNames.length];
        for (int i = 0; i < attributeNames.length; i++)
            this.attributes[i] = AccessAttribute.valueOf(attributeNames[i].toUpperCase());
    }

    public boolean has(AccessAttribute attribute)
    {
        for (AccessAttribute attr : this.attributes)
        {
            if (attr == attribute)
                return true;
        }
        return false;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        for (AccessAttribute attribute : this.attributes)
        {
            if (sb.isEmpty())
                sb.append(attribute.getName());
            else
                sb.append(" ");
        }
        return sb.toString();
    }
}
