

/* First created by JCasGen Mon Feb 08 19:26:33 CET 2016 */
package de.twitter.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** The opinion that is determined by the opinion evaluator
 * Updated by JCasGen Wed Feb 10 10:22:39 CET 2016
 * XML source: C:/Users/Edgar/Universität/Praxisprojekt Textanalysewerkzeuge/eclipse/git/TwitterAnalyticsSchmitz/de.unidue.langtech.teaching.pp.schmitz/src/main/resources/desc/type/OpinionType.xml
 * @generated */
public class DetectedOpinion extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(DetectedOpinion.class);
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
  protected DetectedOpinion() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public DetectedOpinion(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public DetectedOpinion(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public DetectedOpinion(JCas jcas, int begin, int end) {
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
    if (DetectedOpinion_Type.featOkTst && ((DetectedOpinion_Type)jcasType).casFeat_opinion == null)
      jcasType.jcas.throwFeatMissing("opinion", "de.twitter.type.DetectedOpinion");
    return jcasType.ll_cas.ll_getStringValue(addr, ((DetectedOpinion_Type)jcasType).casFeatCode_opinion);}
    
  /** setter for opinion - sets can be negative, neutral or positive 
   * @generated
   * @param v value to set into the feature 
   */
  public void setOpinion(String v) {
    if (DetectedOpinion_Type.featOkTst && ((DetectedOpinion_Type)jcasType).casFeat_opinion == null)
      jcasType.jcas.throwFeatMissing("opinion", "de.twitter.type.DetectedOpinion");
    jcasType.ll_cas.ll_setStringValue(addr, ((DetectedOpinion_Type)jcasType).casFeatCode_opinion, v);}    
   
    
  //*--------------*
  //* Feature: opinionScore

  /** getter for opinionScore - gets This will store the accumulated scores of positive/negative rated words
   * @generated
   * @return value of the feature 
   */
  public float getOpinionScore() {
    if (DetectedOpinion_Type.featOkTst && ((DetectedOpinion_Type)jcasType).casFeat_opinionScore == null)
      jcasType.jcas.throwFeatMissing("opinionScore", "de.twitter.type.DetectedOpinion");
    return jcasType.ll_cas.ll_getFloatValue(addr, ((DetectedOpinion_Type)jcasType).casFeatCode_opinionScore);}
    
  /** setter for opinionScore - sets This will store the accumulated scores of positive/negative rated words 
   * @generated
   * @param v value to set into the feature 
   */
  public void setOpinionScore(float v) {
    if (DetectedOpinion_Type.featOkTst && ((DetectedOpinion_Type)jcasType).casFeat_opinionScore == null)
      jcasType.jcas.throwFeatMissing("opinionScore", "de.twitter.type.DetectedOpinion");
    jcasType.ll_cas.ll_setFloatValue(addr, ((DetectedOpinion_Type)jcasType).casFeatCode_opinionScore, v);}    
  }

    