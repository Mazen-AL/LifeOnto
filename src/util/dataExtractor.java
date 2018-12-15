package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;

import ONTO.BioPontologyfactory;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;
import com.hp.hpl.jena.shared.JenaException;
import com.hp.hpl.jena.util.FileManager;

import util.ReadXMLFile;

public class dataExtractor {
	
    //*************************************************************
	// not used any more 
	//*************************************************************
	
	public static void main(String[] args) throws IOException, ParseException {
		
		riskFactorExtractor("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\data\\ica_ontology_updated_aug_27.owl") ;  
	}
	
	public static List<String> dataExtraction(String Keyword,String max) throws IOException {
		// TODO Auto-generated method stub

		List<String> Totaltitles = new ArrayList<String>();
		int count = 0 ; 
		int counttot = 0 ; 
		// get ID of article  for each concepts 
		//Map<String, List<String>> Alzheimerigoldstandard =  
		for (int i = 0 ; i < 1 ; i++)
		{

			String conpt = Keyword ;
			conpt  =    conpt .replace(" ", "%20")  ;
			// esearch 
			String urlStr = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmax=" + max + "&term=" + conpt + "[Title]%20AND%20(%222017/01/01%22[PDAT]%20:%20%222018/12/31%22[PDAT])" ;
			String result = restcalls.httpGet(urlStr);
			if (result == null )
				continue ; 
			List<String> IDs= ReadXMLFile.ReadPubmed(result,"Id") ;
			if (IDs == null)
				continue ;
			for (String id: IDs)
			{
				String urlid ="https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id="+ id + "&retmode=xml" ;
				String resultid = restcalls.httpGet(urlid );
				if (resultid == null )
					continue ; 
				List<String> titles= ReadXMLFile.ReadPubmed(resultid ,"ArticleTitle") ;
				if (titles != null)
				{
					for (String title : titles )
					{
						Totaltitles.add(title) ; 
					}
				}
			}
		}
		
		
        return Totaltitles ; 
	}
	
	
	
	public static void Alzheimer(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<String> Totaltitles = new ArrayList<String>();
		int count = 0 ; 
		int counttot = 0 ; 
		// get ID of article  for each concepts 
		Map<String, List<String>> Alzheimerigoldstandard =  ReadXMLFile.Deserialize("F:\\eclipse64\\eclipse\\AlzheimerGoldstandard") ;
		for (String conpt : Alzheimerigoldstandard.keySet())
		{
			counttot++ ;
			conpt  =    conpt .replace(" ", "%20")  ;
			// esearch 
			String urlStr = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmax=100&term=" + conpt + "[Title]%20AND%20(%222017/01/01%22[PDAT]%20:%20%222018/12/31%22[PDAT])" ;
			String result = restcalls.httpGet(urlStr);
			if (result == null )
				continue ; 
			List<String> IDs= ReadXMLFile.ReadPubmed(result,"Id") ;
			if (IDs == null)
				continue ;
			for (String id: IDs)
			{
				// efetch 
				//https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=29630950&retmode=xml
				String urlid ="https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id="+ id + "&retmode=xml" ;
				String resultid = restcalls.httpGet(urlid );
				if (resultid == null )
					continue ; 
				List<String> titles= ReadXMLFile.ReadPubmed(resultid ,"ArticleTitle") ;
				if (titles != null)
				{
					for (String title : titles )
					{
						Totaltitles.add(title) ; 
					}
				}
			}
			count++ ;
			if (count == 100)
			{
				count = 0 ;
				ReadXMLFile.Serializeddir(Totaltitles, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesAlzforconcepts.dat");
			}
		}
		
		ReadXMLFile.Serializeddir(Totaltitles, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesAlzforconcepts.dat");

	}
	
	public static void VTE(String[] args) throws IOException {
		// TODO Auto-generated method stub

		List<String> Totaltitles = new ArrayList<String>();
		int count = 0 ; 
		int counttot = 0 ; 
		// get ID of article  for each concepts 
		//Map<String, List<String>> Alzheimerigoldstandard =  
		for (int i = 0 ; i < 1 ; i++)
		{

			String conpt = "Venous thromboembolism" ;
			conpt  =    conpt .replace(" ", "%20")  ;
			// esearch 
			String urlStr = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmax=1000&term=" + conpt + "[Title]%20AND%20(%222017/01/01%22[PDAT]%20:%20%222018/12/31%22[PDAT])" ;
			String result = restcalls.httpGet(urlStr);
			if (result == null )
				continue ; 
			List<String> IDs= ReadXMLFile.ReadPubmed(result,"Id") ;
			if (IDs == null)
				continue ;
			for (String id: IDs)
			{
				// efetch 
				//https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=29630950&retmode=xml
				String urlid ="https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id="+ id + "&retmode=xml" ;
				String resultid = restcalls.httpGet(urlid );
				if (resultid == null )
					continue ; 
				List<String> titles= ReadXMLFile.ReadPubmed(resultid ,"ArticleTitle") ;
				if (titles != null)
				{
					for (String title : titles )
					{
						Totaltitles.add(title) ; 
					}
				}
			}
			count++ ;
			if (count == 100)
			{
				count = 0 ;
				ReadXMLFile.Serializeddir(Totaltitles, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesVTE.dat");
			}
		}
		
		ReadXMLFile.Serializeddir(Totaltitles, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesVTE.dat");

	}
	
	/// extracted from MEDLINE for all titles that has Alzheimer for 01/2017-04/2018
	public static List<String> getAlzheimerTitles(String[] args) {
		// TODO Auto-generated method stub
		List<String> titleList = null ; 
		try {
			 
			titleList = ReadXMLFile.Deserializedirlis("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesAlz.dat") ;
			if (titleList != null)
			{
				return   titleList ; 
			}
			else
			{
				new ArrayList<String>();
			}
			
			File filename  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\data\\Alzheimer2017_18_Title.txt") ;
			List<String> patts = readfiles.readLinesbylines(filename.toURL()) ;
			String concept = "Alzheimer" ; 

			try {
			for (int i = 0; i < patts.size(); i++)
	        {
		        String text =  patts.get(i) ; 
		    	//System.out.println(text);
		    	if (text.toLowerCase().contains(concept.toLowerCase()) )
		    		titleList.add(text);
	        }
			}
			 catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
			
			
	        ReadXMLFile.Serializeddir(titleList, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesAlz.dat");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return titleList ; 
		
	}
	
	
	
	
	/// extracted from MEDLINE for all titles that has Alzheimer for 01/2017-04/2018
		public static List<String> getAlzheimerTitlesForallConcepts() {
			// TODO Auto-generated method stub
			List<String> titleList = null ; 
				titleList = ReadXMLFile.Deserializedirlis("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesAlzforconcepts.dat") ;
				if (titleList != null)
				{
					return   titleList ; 
				}
				else
				{
					return null ; 
				}
			
		}
		
		
		public static void aneurysm(String[] args) throws IOException {
			// TODO Auto-generated method stub

			List<String> Totaltitles = new ArrayList<String>();
			int count = 0 ; 
			int counttot = 0 ; 
			// get ID of article  for each concepts 
			//Map<String, List<String>> Alzheimerigoldstandard =  
			for (int i = 0 ; i < 1 ; i++)
			{

				String conpt = "Intracranial aneurysm" ;
				conpt  =    conpt .replace(" ", "%20")  ;
				// esearch 
				String urlStr = "https://eutils.ncbi.nlm.nih.gov/entrez/eutils/esearch.fcgi?db=pubmed&retmax=1000&term=" + conpt + "[Title]%20AND%20(%222000/01/01%22[PDAT]%20:%20%222018/12/31%22[PDAT])" ;
				String result = restcalls.httpGet(urlStr);
				if (result == null )
					continue ; 
				List<String> IDs= ReadXMLFile.ReadPubmed(result,"Id") ;
				if (IDs == null)
					continue ;
				for (String id: IDs)
				{
					// efetch 
					//https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id=29630950&retmode=xml
					String urlid ="https://eutils.ncbi.nlm.nih.gov/entrez/eutils/efetch.fcgi?db=pubmed&id="+ id + "&retmode=xml" ;
					String resultid = restcalls.httpGet(urlid );
					if (resultid == null )
						continue ; 
					List<String> titles= ReadXMLFile.ReadPubmed(resultid ,"ArticleTitle") ;
					if (titles != null)
					{
						for (String title : titles )
						{
							Totaltitles.add(title) ; 
						}
					}
					
					count++ ;
					if (count == 100)
					{
						break ; 
						//count = 0 ;
						//ReadXMLFile.Serializeddir(Totaltitles, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesVTE.dat");
					}
				}

			}
			
			ReadXMLFile.Serializeddir(Totaltitles, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\titlesaneurysm.dat");

		}
		
		
		public static void semanticGroup(String[] args) throws IOException
		{
			// TODO Auto-generated method stub

			List<String> Totaltitles = new ArrayList<String>();
			Map<String, String> SemanticDir   = new HashMap<String, String>();

			File filename  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\TripleExtraction\\umls\\SemGroups_2013.txt") ;
			List<String> lines = readfiles.readLinesbylines(filename.toURL()) ;
			
			for (String line:lines)
			{
				String tokens[] = line.split("\\|"); 
				SemanticDir.put(tokens[2], tokens[1]); 
				
			}
			
			
			ReadXMLFile.Serializeddiectionary(SemanticDir, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\SemanticGroupDir.dat");

		}
	
		public static void semanticGroupAbbr(String[] args) throws IOException
		{
			// TODO Auto-generated method stub

			List<String> Totaltitles = new ArrayList<String>();
			Map<String, String> SemanticDir   = new HashMap<String, String>();
			Map<String, String> SemanticDir1   = new HashMap<String, String>();
			Map<String, String> SemanticDir2   = new HashMap<String, String>();

			File filename  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\TripleExtraction\\umls\\SemGroups_2013.txt") ;
			List<String> lines = readfiles.readLinesbylines(filename.toURL()) ;
			
			for (String line:lines)
			{
				String tokens[] = line.split("\\|"); 
				SemanticDir.put(tokens[2], tokens[1]); 
				
			}
			
			
			File filename1  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\TripleExtraction\\umls\\SemanticTypes_2013AA.txt") ;
			List<String> lines1 = readfiles.readLinesbylines(filename1.toURL()) ;
			for (String line:lines1)
			{
				String tokens[] = line.split("\\|"); 
				SemanticDir1.put(tokens[1], tokens[0]); 
				
			}
			
			for (String gp:SemanticDir1.keySet())
			{
				SemanticDir.get(gp); 
				SemanticDir2.put(SemanticDir1.get(gp), SemanticDir.get(gp));
			}
			
			
			
			ReadXMLFile.Serializeddiectionary(SemanticDir2, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\SemanticGroupDirAbbr.dat");

		}
		
		public static void semanticTypeDir(String[] args) throws IOException
		{
			// TODO Auto-generated method stub

	
			Map<String, String> SemanticDir1   = new HashMap<String, String>();
			
			File filename1  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\TripleExtraction\\umls\\SemanticTypes_2013AA.txt") ;
			List<String> lines1 = readfiles.readLinesbylines(filename1.toURL()) ;
			for (String line:lines1)
			{
				String tokens[] = line.split("\\|"); 
				SemanticDir1.put(tokens[1], tokens[2]); 
				
			}

			ReadXMLFile.Serializeddiectionary(SemanticDir1, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\SemanticTypeDir.dat");

		}
		
		
		public static void NCBI_Goldstandard_Data(String[] args) throws IOException
		{
			// TODO Auto-generated method stub

	
			Map<String, List<String>> NCBIGold   = new HashMap<String, List<String>>();
			
			File filename1  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\data\\NCBI\\NCBI_corpus_development.txt") ;
			List<String> lines1 = readfiles.readLinesbylines(filename1.toURL()) ;
			List<String> diseases = new ArrayList<String>();
			
			for (String line:lines1)
			{
				
				String mentions[] = StringUtils.substringsBetween(line, "<category=\"Modifier\">", "</category>");
				if (mentions != null)
				{
					for (String  mention:mentions)
						diseases.add(mention.toLowerCase()) ;
				}
				
				String mentions1[] = StringUtils.substringsBetween(line, "<category=\"SpecificDisease\">", "</category>");
				if (mentions1 != null)
				{
					for (String  mention:mentions1)
						diseases.add(mention.toLowerCase()) ;
				}
				
				String mentions2[] = StringUtils.substringsBetween(line, "<category=\"DiseaseClass\">", "</category>");
				if (mentions2 != null)
				{
					for (String  mention:mentions2)
						diseases.add(mention.toLowerCase()) ;
				}
				
				
				NCBIGold.put(line, diseases);
				
				
				
			}
			ReadXMLFile.Serialized(NCBIGold, "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\data\\NCBI\\NCBI_corpus_development.dat");

		}
		
		public static OntModel riskFactorExtractor(String ontoFile) throws IOException, ParseException
		{
			// TODO Auto-generated method stub
			   String  rdfs = "http://www.w3.org/2000/01/rdf-schema#" ;
			
			// read risk factors entered by expert 
			Map<String, String> RiskFactors   = new HashMap<String, String>();

			String owlfile  = "C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\data\\icatest.owl" ;
			File filename  = new File("C:\\Users\\mazina\\Desktop\\School\\Khalid\\Paper\\Distance Supervision NER\\Data Medline_PubMed\\data\\expertRiskFactorstest.txt") ;
			List<String> lines = readfiles.readLinesbylines(filename.toURL()) ;
			
			for (String line:lines)
			{
				String tokens[] = line.split("\\|"); 
				if (tokens.length >1)
				{
					 //  riskfactor, Category 
					RiskFactors.put(tokens[0].toLowerCase(), tokens[1]); 
				}
				
			}
			
			Resource r = null ;
			Resource superclass = null ;
			OntModel OntoGraph = getOntologyModel(ontoFile) ;
			
			// create class for Aneurysm risk factor if not exist and assign a category 
			
			for (String rFactor:RiskFactors.keySet())
			{
				String rfCategoryURI = "http://www.mii.ucla.edu/~willhsu/ontologies/ica_ontology#" + RiskFactors.get(rFactor) ;
				String rfURI = "http://www.mii.ucla.edu/~willhsu/ontologies/ica_ontology#" + rFactor.replace(" ", "_") ;
				
				if ( ( r= OntoGraph.getOntClass(rfURI) ) == null)
				{
					
					OntClass rec = OntoGraph.createClass(rfURI);
					final Property label = ResourceFactory.createProperty(rdfs + "label") ;
					rec.addProperty(label, rFactor);
					BioPontologyfactory.synonymToOnto(rFactor,rfURI,OntoGraph) ;
					
					if ((r= OntoGraph.getOntClass(rfCategoryURI)) != null) ;
					   rec.addSuperClass(r);
				}
			}
			
			OntoGraph.write(System.out, "RDF/XML-ABBREV") ;
			writeOntologyModel(OntoGraph,owlfile) ;
			return OntoGraph ; 
		}
		
		public static OntModel getOntologyModel(String ontoFile)
		{   
		    OntModel ontoModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);
		    try 
		    {
		        InputStream in = FileManager.get().open(ontoFile);
		        try 
		        {
		            ontoModel.read(in, null);
		        } 
		        catch (Exception e) 
		        {
		            e.printStackTrace();
		        }
		    } 
		    catch (JenaException je) 
		    {
		        System.err.println("ERROR" + je.getMessage());
		        je.printStackTrace();
		        System.exit(0);
		    }
		    return ontoModel;
		}
		
		public static void writeOntologyModel(OntModel graphModel, String ontoFile) throws FileNotFoundException
		{   
		    
	        // Creating a File object that represents the disk file. 
	        PrintStream o = new PrintStream(new File(ontoFile)); 
	  
	        // Store current System.out before assigning a new value 
	        PrintStream console = System.out; 
	  
	        // Assign o to output stream 
	        System.setOut(o); 
	         
	        graphModel.write(System.out, "RDF/XML-ABBREV") ;
	        // Use stored value for output stream 
	        System.setOut(console); 
		}
		
}
