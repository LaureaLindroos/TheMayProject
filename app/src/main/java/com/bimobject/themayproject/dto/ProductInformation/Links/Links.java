package com.bimobject.themayproject.dto.ProductInformation.Links;

public class Links {
    public String externalProductUrl;
    public String installationInstructionUrl;
    public String cobieProductDataSheetUrl;
    public String productCertificationUrl;
    public String technicalDescriptionUrl;
    public String instructionVideoUrl;


    public Links(String externalProductUrl, String installationInstructionUrl, String cobieProductDataSheetUrl, String productCertificationUrl, String technicalDescriptionUrl, String instructionVideoUrl) {
        this.externalProductUrl = externalProductUrl;
        this.installationInstructionUrl = installationInstructionUrl;
        this.cobieProductDataSheetUrl = cobieProductDataSheetUrl;
        this.productCertificationUrl = productCertificationUrl;
        this.technicalDescriptionUrl = technicalDescriptionUrl;
        this.instructionVideoUrl = instructionVideoUrl;
    }

    public String getExternalProductUrl() {
        return externalProductUrl;
    }

    public void setExternalProductUrl(String externalProductUrl) {
        this.externalProductUrl = externalProductUrl;
    }

    public String getInstallationInstructionUrl() {
        return installationInstructionUrl;
    }

    public void setInstallationInstructionUrl(String installationInstructionUrl) {
        this.installationInstructionUrl = installationInstructionUrl;
    }

    public String getCobieProductDataSheetUrl() {
        return cobieProductDataSheetUrl;
    }

    public void setCobieProductDataSheetUrl(String cobieProductDataSheetUrl) {
        this.cobieProductDataSheetUrl = cobieProductDataSheetUrl;
    }

    public String getProductCertificationUrl() {
        return productCertificationUrl;
    }

    public void setProductCertificationUrl(String productCertificationUrl) {
        this.productCertificationUrl = productCertificationUrl;
    }

    public String getTechnicalDescriptionUrl() {
        return technicalDescriptionUrl;
    }

    public void setTechnicalDescriptionUrl(String technicalDescriptionUrl) {
        this.technicalDescriptionUrl = technicalDescriptionUrl;
    }

    public String getInstructionVideoUrl() {
        return instructionVideoUrl;
    }

    public void setInstructionVideoUrl(String instructionVideoUrl) {
        this.instructionVideoUrl = instructionVideoUrl;
    }
}
