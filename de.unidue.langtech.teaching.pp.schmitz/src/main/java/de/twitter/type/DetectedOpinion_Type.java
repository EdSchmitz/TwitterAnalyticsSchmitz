
/* First created by JCasGen Mon Feb 08 19:26:33 CET 2016 */
package de.twitter.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** The opinion that is determined by the opinion evaluator
 * Updated by JCasGen Wed Feb 10 10:22:39 CET 2016
 * @generated */
public class DetectedOpinion_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (DetectedOpinion_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = DetectedOpinion_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new DetectedOpinion(addr, DetectedOpinion_Type.this);
  			   DetectedOpinion_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new DetectedOpinion(addr, DetectedOpinion_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = DetectedOpinion.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.twitter.type.DetectedOpinion");
 
  /** @generated */
  final Feature casFeat_opinion;
  /** @generated */
  final int     casFeatCode_opinion;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getOpinion(int addr) {
        if (featOkTst && casFeat_opinion == null)
      jcas.throwFeatMissing("opinion", "de.twitter.type.DetectedOpinion");
    return ll_cas.ll_getStringValue(addr, casFeatCode_opinion);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOpinion(int addr, String v) {
        if (featOkTst && casFeat_opinion == null)
      jcas.throwFeatMissing("opinion", "de.twitter.type.DetectedOpinion");
    ll_cas.ll_setStringValue(addr, casFeatCode_opinion, v);}
    
  
 
  /** @generated */
  final Feature casFeat_opinionScore;
  /** @generated */
  final int     casFeatCode_opinionScore;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public float getOpinionScore(int addr) {
        if (featOkTst && casFeat_opinionScore == null)
      jcas.throwFeatMissing("opinionScore", "de.twitter.type.DetectedOpinion");
    return ll_cas.ll_getFloatValue(addr, casFeatCode_opinionScore);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOpinionScore(int addr, float v) {
        if (featOkTst && casFeat_opinionScore == null)
      jcas.throwFeatMissing("opinionScore", "de.twitter.type.DetectedOpinion");
    ll_cas.ll_setFloatValue(addr, casFeatCode_opinionScore, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public DetectedOpinion_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_opinion = jcas.getRequiredFeatureDE(casType, "opinion", "uima.cas.String", featOkTst);
    casFeatCode_opinion  = (null == casFeat_opinion) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_opinion).getCode();

 
    casFeat_opinionScore = jcas.getRequiredFeatureDE(casType, "opinionScore", "uima.cas.Float", featOkTst);
    casFeatCode_opinionScore  = (null == casFeat_opinionScore) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_opinionScore).getCode();

  }
}



    