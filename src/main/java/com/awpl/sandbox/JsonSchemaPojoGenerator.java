package com.awpl.sandbox;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsonschema2pojo.DefaultGenerationConfig;
import org.jsonschema2pojo.GenerationConfig;
import org.jsonschema2pojo.Jackson2Annotator;
import org.jsonschema2pojo.SchemaGenerator;
import org.jsonschema2pojo.SchemaMapper;
import org.jsonschema2pojo.SchemaStore;
import org.jsonschema2pojo.rules.RuleFactory;

import com.sun.codemodel.JCodeModel;

public class JsonSchemaPojoGenerator
{

	public static void main(String[] args) throws IOException
	{
		JCodeModel codeModel = new JCodeModel();

		URL source = new URL("file:./src/main/resources/schema.json");

		GenerationConfig config = new DefaultGenerationConfig() {
		@Override
		public boolean isGenerateBuilders() { // set config option by overriding method
		return false;
		}
		@Override
			public boolean isIncludeAdditionalProperties()
			{
				return false;
			}
		@Override
			public boolean isUseLongIntegers()
			{
				return true;
			}
		};

		RuleFactory ruleFactory = new RuleFactory(config, new JpaAnnotator(), new SchemaStore());
		SchemaMapper mapper = new SchemaMapper(ruleFactory, new SchemaGenerator());
		mapper.generate(codeModel, "", "com.awpl.sandbox.domain", source);

		codeModel.build(new File("./src/main/java/"));
	}

}
