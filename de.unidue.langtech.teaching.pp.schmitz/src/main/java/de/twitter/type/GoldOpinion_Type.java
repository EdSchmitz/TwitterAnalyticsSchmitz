
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

/** The actual opinion shown in this tweet
 * Updated by JCasGen Mon Feb 08 19:26:33 CET 2016
 * @generated */
public class GoldOpinion_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GoldOpinion_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GoldOpinion_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GoldOpinion(addr, GoldOpinion_Type.this);
  			   GoldOpinion_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GoldOpinion(addr, GoldOpinion_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = GoldOpinion.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.twitter.type.GoldOpinion");
 
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
      jcas.throwFeatMissing("opinion", "de.twitter.type.GoldOpinion");
    return ll_cas.ll_getStringValue(addr, casFeatCode_opinion);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setOpinion(int addr, String v) {
        if (featOkTst && casFeat_opinion == null)
      jcas.throwFeatMissing("opinion", "de.twitter.type.GoldOpinion");
    ll_cas.ll_setStringValue(addr, casFeatCode_opinion, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public GoldOpinion_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_opinion = jcas.getRequiredFeatureDE(casType, "opinion", "uima.cas.String", featOkTst);
    casFeatCode_opinion  = (null == casFeat_opinion) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_opinion).getCode();

  }
}



    