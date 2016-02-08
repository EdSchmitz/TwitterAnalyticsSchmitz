

/* First created by JCasGen Mon Feb 08 19:26:33 CET 2016 */
package de.twitter.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** The actual opinion shown in this tweet
 * Updated by JCasGen Mon Feb 08 19:26:33 CET 2016
 * XML source: C:/Users/Edgar/Universität/Praxisprojekt Textanalysewerkzeuge/eclipse/git/TwitterAnalyticsSchmitz/de.unidue.langtech.teaching.pp.schmitz/src/main/java/de/twitter/types/OpinionType.xml
 * @generated */
public class GoldOpinion extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GoldOpinion.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected GoldOpinion() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GoldOpinion(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GoldOpinion(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GoldOpinion(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: opinion

  /** getter for opinion - gets can be negative, neutral or positive
   * @generated
   * @return value of the feature 
   */
  public String getOpinion() {
    if (GoldOpinion_Type.featOkTst && ((GoldOpinion_Type)jcasType).casFeat_opinion == null)
      jcasType.jcas.throwFeatMissing("opinion", "de.twitter.type.GoldOpinion");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GoldOpinion_Type)jcasType).casFeatCode_opinion);}
    
  /** setter for opinion - sets can be negative, neutral or positive 
   * @generated
   * @param v value to set into the feature 
   */
  public void setOpinion(String v) {
    if (GoldOpinion_Type.featOkTst && ((GoldOpinion_Type)jcasType).casFeat_opinion == null)
      jcasType.jcas.throwFeatMissing("opinion", "de.twitter.type.GoldOpinion");
    jcasType.ll_cas.ll_setStringValue(addr, ((GoldOpinion_Type)jcasType).casFeatCode_opinion, v);}    
  }

    