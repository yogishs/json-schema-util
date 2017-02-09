package com.awpl.sandbox;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.apache.commons.lang3.text.WordUtils;
import org.jsonschema2pojo.Jackson2Annotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JStatement;
import com.sun.codemodel.JType;
import com.sun.codemodel.JVar;

/**
 *
 * @author <a href="mailto:samuel.goldmann@edocsol.com">Sam Goldmann</a>
 */
public class JpaAnnotator extends Jackson2Annotator
{

	private static final String PERSISTENCE_NODE_PROPERTY = "persistence";

	private static final String MAPPED_SUPERCLASS_PROPERTY = "mappedSuperclass";

	private static final String ENTITY_NODE_PROPERTY = "entity";

	private static final String NAME_PROPERTY = "name";

	private static final String ENTITY_NAME_PARAM = "name";

	private static final JType[] NO_ARGS = new JType[0];

	@Override
	public void propertyInclusion(JDefinedClass clazz, JsonNode schema)
	{
		JsonNode persistenceNode = schema.get(PERSISTENCE_NODE_PROPERTY);
		if (persistenceNode != null)
		{
			JsonNode mappedSuperclassNode = persistenceNode.get(MAPPED_SUPERCLASS_PROPERTY);
			if (mappedSuperclassNode != null && mappedSuperclassNode.asBoolean())
			{
				clazz.annotate(MappedSuperclass.class);
			}
			else
			{
				JAnnotationUse annotationUse = clazz.annotate(Entity.class);

				JsonNode entityNode = persistenceNode.get(ENTITY_NODE_PROPERTY);
				JsonNode entityNameNode = (entityNode != null) ? entityNode.get(NAME_PROPERTY) : null;
				String entityName = (entityNameNode != null) ? entityNameNode.asText() : null;
				if (entityName != null && !entityName.isEmpty())
				{
					annotationUse.param(ENTITY_NAME_PARAM, entityName);
				}
			}
		}
	}

	@Override
	public void additionalPropertiesField(JFieldVar field, JDefinedClass clazz, String propertyName)
	{
		field.annotate(Transient.class);
	}

	public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode)
	{
		super.propertyField(field, clazz, propertyName, propertyNode);
		if (propertyNode.get("type").textValue().contains("array"))
		{
			String mappedByFieldName = WordUtils.uncapitalize(clazz.name());
			JAnnotationUse annotation = field.annotate(OneToMany.class);
			annotation.param("mappedBy", mappedByFieldName);
			annotation.param("cascade", CascadeType.ALL);
			String pattern = "<(.*?)>";

			// Create a Pattern object
			Pattern r = Pattern.compile(pattern);

			// Now create matcher object.
			Matcher m = r.matcher(field.type().name());
			if (m.find())
			{
				JDefinedClass referencedClass = clazz._package()._getClass(m.group(1));

				JFieldVar mappedByField = referencedClass.field(JMod.PRIVATE, clazz, mappedByFieldName);
				mappedByField.annotate(ManyToOne.class);
				final JMethod mappedBySetter = referencedClass.method(JMod.PUBLIC, clazz.owner().VOID, "set" + clazz.name());
				final JVar setterParam = mappedBySetter.param(clazz, mappedByFieldName);
				mappedBySetter.body().add((JStatement) JExpr._this().ref(mappedByField).assign(setterParam));

				final JMethod mappedByGetter = referencedClass.method(JMod.PUBLIC, clazz, "get" + clazz.name());
				mappedByGetter.body()._return(JExpr._this().ref(mappedByField));
				JMethod preUpdateMethod = clazz.method(JMod.PUBLIC, clazz.owner().VOID, "prePersist");
				preUpdateMethod.annotate(PrePersist.class);
				StringBuilder preUpdateMethodSB = new StringBuilder();
				preUpdateMethodSB.append("for(");
				preUpdateMethodSB.append(referencedClass.name());
				preUpdateMethodSB.append(" entry : ");
				preUpdateMethodSB.append(field.name());
				preUpdateMethodSB.append(")\n\t{\n");
				preUpdateMethodSB.append("\tentry.set");
				preUpdateMethodSB.append(clazz.name());
				preUpdateMethodSB.append("(this);\n\t}");
				preUpdateMethod.body().directStatement(preUpdateMethodSB.toString());
			}

		}

		if (propertyNode.has("primaryKey"))
		{
			JAnnotationUse idAnnotation = field.annotate(Id.class);
			JAnnotationUse genAnnotation = field.annotate(GeneratedValue.class);
			genAnnotation.param("strategy", GenerationType.AUTO);
		}

		if (propertyNode.get("type").textValue().contains("object"))
		{
			JAnnotationUse oneToOneAnnotation = field.annotate(OneToOne.class);
			oneToOneAnnotation.param("cascade", CascadeType.ALL);
		}
	}
}
