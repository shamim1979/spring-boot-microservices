package bd.gov.lims.common.route;

public abstract class ApiProvider {
    public static final String SEPARATOR = "/";
    public static final String DASH = "-";
    public static final String BASEPATH = SEPARATOR + "api";
    public static final String OPENBASE_PATH = "open";
    public static final String VERSION = "/v1";
    public static final String LOGIN_PATH = BASEPATH + SEPARATOR + "login";
    public static final String OPEN_PATH = BASEPATH + SEPARATOR + "v1/open/**";
    public static final String SWAGGER_PATH = SEPARATOR + "swagger-ui/**";
    public static final String SWAGGER_LIMS_PATH = SEPARATOR + "lims-doc";
    public static final String API_DOCS_PATH = SEPARATOR + "v3/api-docs/**";

    public static final String OPEN_PARENTHESIS = "{";
    public static final String CLOSE_PARENTHESIS = "}";
    public static final String IDENTIFIER = SEPARATOR + OPEN_PARENTHESIS + "id" + CLOSE_PARENTHESIS;

    public static class AppUser {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "users";
        public static final String REFRESH_TOKEN = "/refresh-token";
        public static final String ME_INFO = "/me";
        public static final String USER_LOCK =  IDENTIFIER + SEPARATOR + "lock";
        public static final String OFFICER = "appuser-officer";

        public static final String REPRESENTATIVE = "representative-user";
        public static final String REPRESENTATIVE_UPDATE = "representative-user" + IDENTIFIER;
        public static final String REPRESENTATIVE_USER_LIST = "representative-user-list";
        public static final String OFFICER_UPDATE = "appuser-officer"+IDENTIFIER;
        public static final String REVOKE_OFFICE_USER= IDENTIFIER + SEPARATOR + "revoke-office-user";
    }

    public static class AppUserOfficer {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "appuser-officer";
        public static final String OFFICER_LOCK =  IDENTIFIER + SEPARATOR + "lock";
    }

    public static class Employee {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "employees";
        public static final String ME_INFO = "/me";
    }

    public static class AppUserAssignRole {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "users/assign-role";
    }

    public static class ForgetPassword {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "open" + SEPARATOR + "reset-password";
        public static final String RESET_PASSWORD_VERIFY_OTP = "/verify-otp/{tokenId}";
        public static final String NEW_PASSWORD = "/{tokenId}";
        public static final String RESET_PASSWORD_RESEND_OTP = "/resend-otp/{tokenId}";
    }
    public static class AdminForgotPassword {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "open" + SEPARATOR +"admin"+SEPARATOR+ "reset-password";
        public static final String RESET_PASSWORD_VERIFY_TOKEN = "/verify-token/{token}";
        public static final String RESET_PASSWORD_NEW_PASSWORD_AND_VERIFY_OTP = "/new-password";
        public static final String NEW_PASSWORD = "/{tokenId}";
        public static final String RESET_PASSWORD_RESEND_OTP = "/resend-otp/{tokenId}";
    }

    public static class Role {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "roles";
        public static final String ASSIGN_PRIVILEGE = IDENTIFIER + "/assign-privilege";
    }

    public static class Privilege {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "privileges";
        public static final String PRIVILEGE_IDENTIFIER = "privilegeId";
        public static final String PRIVILEGE_PATHVARIABLE = SEPARATOR + OPEN_PARENTHESIS + PRIVILEGE_IDENTIFIER + CLOSE_PARENTHESIS;
    }

    public static class AppUtility {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "utility";
        public static final String SMSPATH = ROOTPATH + SEPARATOR + "sms";

        public static final String SMS_SEND_ENDPOINT = "";
    }

    /************** Organization Module *****************/

    public static class AppointmentActivityRole {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "appointment-activity-role";
        public static final String APPOINTMENT_ACTIVITY_ROLE_IDENTIFIER = IDENTIFIER;
    }

    public static class Appointment {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "appointments";
        public static final String APPOINTMENT_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
        public static final String AVAILABLE = SEPARATOR+"{organizationId}"+SEPARATOR + "available";
    }
    public static class AppointmentHistory {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "appointment-histories";
        public static final String APPOINTMENT_IDENTIFIER = IDENTIFIER;
    }

    public static class Designation {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "designations";
        public static final String DESIGNATION_IDENTIFIER = IDENTIFIER;
    }

    public static class MilitaryRank {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "military-ranks";
        public static final String MILITARY_RANK_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class OfficeCategories {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "office-categories";
        public static final String OFFICE_CATEGORIES_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class OfficeIssues {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "office-issues";
        public static final String OFFICE_ISSUES_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Office {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "office";
        public static final String OFFICE_IDENTIFIER = IDENTIFIER;
        public static final String MARK_AS_REGISTRATION_OFFICE = SEPARATOR + "mark-as-registration-office" + IDENTIFIER;
        public static final String WING_LIST = SEPARATOR + "dgdp-wings";
        public static final String SECTION_LIST = SEPARATOR + "dgdp-sections";
        public static final String ORGANOGRAM_IDENTIFIER = SEPARATOR + "organogram";
        public static final String ME_IDENTIFIER = SEPARATOR + "me";
        public static final String ORGANOGRAM_ID_IDENTIFIER = OFFICE_IDENTIFIER + ORGANOGRAM_IDENTIFIER;

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class OfficeLevel {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "office-levels";
        public static final String OFFICE_LEVEL_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
        public static final String OFFICE_OFFICE_LEVEL_IDENTIFIER =  SEPARATOR + "office"+IDENTIFIER;;
    }
    public static class Board {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "boards";
        public static final String BOARD_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class BoardMemberPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "board_member";
        public static final String BOARD_MEMBER_IDENTIFIER = IDENTIFIER;
    }

    public static class OfficeRole {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "office-roles";
        public static final String OFFICE_ROLE_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class OfficeUser {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "office-users";
        public static final String OFFICE_USER_IDENTIFIER = IDENTIFIER;
        public static final String OFFICE_USER_ASSIGN = IDENTIFIER + SEPARATOR + "assign-user";
        public static final String OFFICE_USER_OTP_VALIDITY_INIT = IDENTIFIER + SEPARATOR + OPEN_PARENTHESIS + "type" + CLOSE_PARENTHESIS + SEPARATOR + "otp-validity/init";
        public static final String OFFICE_USER_OTP_VALIDITY_RESEND = IDENTIFIER + SEPARATOR + OPEN_PARENTHESIS + "type" + CLOSE_PARENTHESIS + SEPARATOR + "otp-validity/resend" + SEPARATOR + OPEN_PARENTHESIS + "tokenId" + CLOSE_PARENTHESIS;
        public static final String OFFICE_USER_OTP_VALIDITY_VERIFY = IDENTIFIER + SEPARATOR + OPEN_PARENTHESIS + "type" + CLOSE_PARENTHESIS + SEPARATOR + "otp-validity/verify" + SEPARATOR + OPEN_PARENTHESIS + "tokenId" + CLOSE_PARENTHESIS;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
        public static final String REVOKE_APPOINTMENT = IDENTIFIER + SEPARATOR + "revoke-appointment";

        public static final String OFFICE_USER_ADMIN_CREATE = "office-admin";

        public static final String OFFICE_USER_RESET_PASSWORD = "reset-password";
        public static final String OFFICE_USER_RESET_PASSWORD_INIT = OFFICE_USER_RESET_PASSWORD + SEPARATOR + "init" + IDENTIFIER;
        public static final String OFFICE_ADMIN_USER_RESET_PASSWORD_INIT = OFFICE_USER_RESET_PASSWORD + SEPARATOR + "admin-user-init" + IDENTIFIER;
        public static final String OFFICE_USER_RESET_PASSWORD_SUBMIT = OFFICE_USER_RESET_PASSWORD + SEPARATOR + "submit" + IDENTIFIER;
        public static final String ASSIGN_APPOINTMENT = IDENTIFIER + SEPARATOR + "assign-appointments";
        public static final String ASSIGN_ADDITIONAL_APPOINTMENT = IDENTIFIER + SEPARATOR + "assign-additional-appointments";
        public static final String REVOKE_ADDITIONAL_APPOINTMENT = IDENTIFIER + SEPARATOR + "revoke-additional-appointments";
        public static final String ACTIVE_APPOINTMENT_IDENTIFIER = IDENTIFIER + SEPARATOR + "active-appointments";
    }

    /*********************** i18n-service *****************/
    public static class Language {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "language";
        public static final String LANGUAGE_IDENTIFIER = IDENTIFIER;
        public static final String LANGUAGE_REVERSE_ENGINEERING = "language-reverse-engineering";
    }

    public static class Modules {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "modules";
        public static final String MODULE_IDENTIFIER = IDENTIFIER;
    }

    public static class SubModules {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "submodules";
        public static final String SUB_MODULE_IDENTIFIER = IDENTIFIER;
    }

    /*********************** indent-service *****************/

    public static class ApprovalPrinciple {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-in-principles";
        public static final String APPROVAL_PRINCIPLE_IDENTIFIER = IDENTIFIER;
        public static final String VETTING_IDENTIFIER = IDENTIFIER + SEPARATOR + "vetting";
        public static final String APPROVAL_PRINCIPLE_UPDATE_STATUS = IDENTIFIER + SEPARATOR + "update_status";
    }
    public static class ApprovalInPrincipleLetter {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-in-principle-letters";
        public static final String APPROVAL_IN_PRINCIPLE_LETTER_IDENTIFIER = IDENTIFIER;
    }

    public static class Indent {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "indents";
        public static final String INDENT_IDENTIFIER = IDENTIFIER;
        public static final String INDENT_TO_ADVANCED_INDENT = "advanced_indent" + IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER+ SEPARATOR + "forwarded-to-dgdp";
        public static final String FORWARDED_TO_INSPECTOR = IDENTIFIER+ SEPARATOR + "{inspector_id}" + SEPARATOR + "forwarded-to-inspector";
    }

    public static class IndentTraining {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "indent-trainings";
        public static final String INDENT_TRAINING_IDENTIFIER = IDENTIFIER;
    }
    public static class IndentBookPublication {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "indent-book-publications";
        public static final String INDENT_BOOK_PUBLICATION_IDENTIFIER = IDENTIFIER;
    }

    public static class IndentAnnex {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "indent-annexes";
        public static final String INDENT_ANNEX_IDENTIFIER = IDENTIFIER;
        public static final String INDENT_ANNEX_COLLECTION_DELETE = IDENTIFIER + SEPARATOR + "delete";
        public static final String INDENT_ANNEX_UPLOAD_BY_FILE = "upload-by-file";
    }

    public static class IndentSstSsm {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "indent-sst-ssm";
        public static final String INDENT_SST_SSN_IDENTIFIER = IDENTIFIER;
    }

    public static class ItemBrand {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-brands";
        public static final String ITEM_BRAND_IDENTIFIER = IDENTIFIER;

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class BudgetCode {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "budget-codes";
        public static final String ITEM_BRAND_IDENTIFIER = IDENTIFIER;

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class ItemStandardizationPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-standardization";
        public static final String ITEM_STANDARDIZATION_IDENTIFIER = IDENTIFIER;
    }

    public static class ItemCategory {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-categories";
        public static final String ITEM_CATEGORY_IDENTIFIER = IDENTIFIER;

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Item {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "items";
        public static final String ITEM_IDENTIFIER = IDENTIFIER;
        public static final String ITEM_UPDATE_STATUS = IDENTIFIER + SEPARATOR + "update_status";
        public static final String ITEM_GENERATE_ITEM_NUMBER = ROOTPATH + SEPARATOR + "generate_random_number";
    }

    public static class ItemModel {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-models";
        public static final String ITEM_MODEL_IDENTIFIER = IDENTIFIER;
        public static final String ITEM_MODEL_UPDATE_STATUS = IDENTIFIER + SEPARATOR + "update_status";
    }

    public static class ItemOrganization {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-organizations";
        public static final String ITEM_ORGANIZATION_IDENTIFIER = IDENTIFIER;
        public static final String ITEM_ORGANIZATION_UPDATE_STATUS = IDENTIFIER + SEPARATOR + "update_status";
    }

    public static class ItemUnit {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-units";
        public static final String ITEM_UNIT_IDENTIFIER = IDENTIFIER;

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Specification {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "specifications";
        public static final String SPECIFICATION_IDENTIFIER = IDENTIFIER;
    }

    public static class SpecificationTemplate {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "specification-templates";
        public static final String SPECIFICATION_TEMPLATE_IDENTIFIER = IDENTIFIER;
        public static final String SPECIFICATION_TEMPLATE_UPDATE_STATUS = IDENTIFIER + SEPARATOR + "update_status";
        public static final String SPECIFICATION_TEMPLATE_NAME_GENERATE = SEPARATOR + "generate_name" + SEPARATOR + "{itemId}" + SEPARATOR + "{itemModelId}";
    }
    public static class ItemSpecification {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-specifications";
        public static final String ITEM_SPECIFICATION_IDENTIFIER = IDENTIFIER;
    }
    public static class ItemSpecificationDetails {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "item-specification-details";
        public static final String ITEM_SPECIFICATION_IDENTIFIER = IDENTIFIER;
    }


    public static class SpecificationType {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "specification-types";
        public static final String SPECIFICATION_TYPE_IDENTIFIER = IDENTIFIER;
    }

    public static class SignatoryDetails {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "signatory-details";
        public static final String SIGNATURAE_DETAILS_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_AGREE_TO_SIGNATURE_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-agree-to-signature";

    }

    /*********************** log-service *****************/

    public static class Log {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "logs";
        public static final String LOG_IDENTIFIER = IDENTIFIER;
    }

    /*********************** portal-service *****************/

    public static class Miscellaneous {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "miscellaneous";
        public static final String MISCELLANEOUS_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class News {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "news";
        public static final String NEWS_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class ContactUs {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "contact-us";
        public static final String CONTRACT_US_IDENTIFIER = IDENTIFIER;
    }

    public static class Notice {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "notices";
        public static final String NOTICE_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Portal {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "all-portal-data";
        public static final String PORTAL_IDENTIFIER = IDENTIFIER;
    }

    public static class StaticContent {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "static-contents";
        public static final String STATIC_CONTENT_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class StaticFAQ {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "static-faqs";
        public static final String STATIC_FAQ_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class StaticGallery {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "static-galleries";
        public static final String STATIC_GALLERY_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    /*********************** supplier-service *****************/

    public static class PostApplication {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "post-application";
        public static final String POST_APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String APPLICATION_IDENTIFIER = SEPARATOR + "application";

        public static final String REPRESENTATIVE_SET = IDENTIFIER + SEPARATOR + "representative";
    }
    public static class PostApplicationOrganization {
        public static final String ROOTPATH = Supplier.ROOTPATH + SEPARATOR + "main-application";
        public static final String POST_APPLICATION_SUPPLIER_CURRENT = "supplier-current";
        public static final String ORGANIZATION = ROOTPATH + SEPARATOR + "organization-information";
        public static final String POST_APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String POST_APPLICATION_BY_SUPPLIER_ID =  SEPARATOR + "admin" + SEPARATOR + OPEN_PARENTHESIS + "supplier_id" + CLOSE_PARENTHESIS;
        public static final String POST_APPLICATION_ORGANIZATION_IDENTIFIER = "current";
        public static final String POST_APPLICATION_ORGANIZATION_IDENTIFIER_SAVE = "current" + SEPARATOR + "save";
        public static final String POST_APPLICATION_ORGANIZATION_IDENTIFIER_FINAL_SAVE = "current" + SEPARATOR + "final-save";
        public static final String APPLICATION_IDENTIFIER = SEPARATOR + "application";
        public static final String APPLICATION_ENLISTMENT_APPLICATION_FORWARD = IDENTIFIER + SEPARATOR + "forward";

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";

        public static final String UPDATE_FINAL_ADMIN_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "admin-update-final-status";
        public static final String UPDATE_FINAL_ADMIN_STATUS_IDENTIFIER_WITH_ADDITIONAL_INFO = IDENTIFIER + SEPARATOR + "admin-update-final-status-with-additional-info";
        public static final String AVAILABLE_OWNER_PARTNER = IDENTIFIER + SEPARATOR + "available-owner-partners";
        public static final String SUPPLIER_ORGANIZATION_OWNER_PARTNER_REPRESENTATIVE =  IDENTIFIER+SEPARATOR + "owner-partners-representatives";

    }

    public static class PostApplicationOwnerInformation {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "main-application-owner-info";
        public static final String POST_APPLICATION_OWNER_IDENTIFIER = IDENTIFIER;
        public static final String APPLICATION_OWNER_IDENTIFIER = SEPARATOR + "application";
    }

    public static class PostApplicationP2 {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "post-application-part2";
        public static final String POST_APPLICATION_P2_IDENTIFIER = IDENTIFIER;
        public static final String APPLICATION_P2_IDENTIFIER = SEPARATOR + "application";
    }

    public static class PreApplication {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "pre-application";
        public static final String PRE_APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String PRE_APPLICATION_MILITARY = IDENTIFIER  + SEPARATOR +  "military-app";
        public static final String APPLICATION_PRE_APPLICATION_IDENTIFIER = SEPARATOR + "application";
        public static final String APPLICATION_PRE_APPLICATION_FORWARD = IDENTIFIER + SEPARATOR + "forward";

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
        public static final String ADMIN_UPDATE_STATUS = IDENTIFIER + SEPARATOR + "admin-update-status";
    }

    public static class DGFIApplication {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "dgfi-application";
        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String APPLICATION_DGFI_APPLICATION_CURRENT = SEPARATOR + "current";
        public static final String APPLICATION_DGFI_APPLICATION_CURRENT_SAVE = APPLICATION_DGFI_APPLICATION_CURRENT + SEPARATOR + "save";
        public static final String APPLICATION_DGFI_APPLICATION_FORWARDING = IDENTIFIER + SEPARATOR + "forwarding";
    }

    public static class RenewalOfDGFIClearance {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "renewal" + SEPARATOR +"dgfi-clearance";
        public static final String RENEWAL_DGFI_CLEARANCE_IDENTIFIER = IDENTIFIER;
        public static final String RENEWAL_DGFI_CLEARANCE_CURRENT = SEPARATOR + "current";
        public static final String RENEWAL_DGFI_CLEARANCE_DGFI = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "dgfi";
        public static final String RENEWAL_DGFI_CLEARANCE_DGFI_UPDATE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "dgfi" + SEPARATOR + "{dgfi_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_DGFI_CURRENT = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "dgfi" + SEPARATOR + "current";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_UPDATE= RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{biodata_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_CURRENT_LIST = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "current" + SEPARATOR + "list";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_CURRENT = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{pre_bio_data_id}" + SEPARATOR + "current";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_SAVE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{pre_bio_data_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_UPDATE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{pre_bio_data_id}" + SEPARATOR + "{current_bio_data_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_WITNESS = RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_UPDATE + SEPARATOR + "witness";
        public static final String UPDATED_DOCUMENT_CURRENT = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "suppliers-updated-document/current";
        public static final String UPDATED_DOCUMENT = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "suppliers-updated-document";
        public static final String UPDATED_DOCUMENT_UPDATE = UPDATED_DOCUMENT + SEPARATOR + OPEN_PARENTHESIS + "updatedDocumentId" + CLOSE_PARENTHESIS;
        public static final String RENEWAL_DGFI_CLEARANCE_CURRENT_FIRST_SAVE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "first-save";
        public static final String RENEWAL_DGFI_CLEARANCE_CURRENT_SAVE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "save";
        public static final String RENEWAL_DGFI_CLEARANCE_FORWARDING = IDENTIFIER + SEPARATOR + "forwarding";
        public static final String RENEWAL_DGFI_CLEARANCE_ADMIN_FIRST_UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "admin-first-update-status";
        public static final String RENEWAL_DGFI_CLEARANCE_ADMIN_FINAL_UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "admin-final-update-status";
        public static final String RENEWAL_DGFI_CLEARANCE_ADMIN_FINAL_UPDATE_STATUS_IDENTIFIER_WITH_PARAM = IDENTIFIER + SEPARATOR + "admin-final-update-status-param";
    }

    public static class SupplierTesting {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "testing";
        public static final String RENEWAL_DGFI_CLEARANCE_IDENTIFIER = IDENTIFIER;
        public static final String RENEWAL_DGFI_CLEARANCE_CURRENT = SEPARATOR + "current";
        public static final String RENEWAL_DGFI_CLEARANCE_DGFI = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "dgfi";
        public static final String RENEWAL_DGFI_CLEARANCE_DGFI_UPDATE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "dgfi" + SEPARATOR + "{dgfi_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_DGFI_CURRENT = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "dgfi" + SEPARATOR + "current";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_UPDATE= RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{biodata_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_CURRENT_LIST = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "current" + SEPARATOR + "list";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_CURRENT = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{pre_bio_data_id}" + SEPARATOR + "current";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_SAVE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{pre_bio_data_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_BIO_DATA_BY_PRE_BIODATA_ID_UPDATE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "bio-data" + SEPARATOR + "{pre_bio_data_id}" + SEPARATOR + "{current_bio_data_id}";
        public static final String RENEWAL_DGFI_CLEARANCE_CURRENT_SAVE = RENEWAL_DGFI_CLEARANCE_CURRENT + SEPARATOR + "save";
        public static final String RENEWAL_DGFI_CLEARANCE_FORWARDING = IDENTIFIER + SEPARATOR + "forwarding";
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class PreApplicationMilitary {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "pre-application-military";
        public static final String PRE_APPLICATION_MILITARY_IDENTIFIER = IDENTIFIER;
        public static final String APPLICATION_PRE_APPLICATION_MILITARY_IDENTIFIER = SEPARATOR + "application";
    }

    public static class Supplier {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "suppliers";
        public static final String SUPPLIER_IDENTIFIER = IDENTIFIER;
    }

    public static class G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "g4n10-pre-application-for-defense";
        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String CURRENT_APPLICATION = SEPARATOR + "application";

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class G4n10EnlistmentApplicationPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "g4n10-application";
        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String CURRENT_APPLICATION = SEPARATOR + "application";

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class G4n10EnlistmentApplicationAttachmentPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "g4n10-application-attachments";
        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String CURRENT_APPLICATION = SEPARATOR + "application";
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class G4n10EnlistmentApplicationLatestAttachmentPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "g4n10-application-latest-attachments";
        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String CURRENT_APPLICATION = SEPARATOR + "application";
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class SupplierPayment {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "suppliers-payment";
        public static final String SUPPLIER_IDENTIFIER = IDENTIFIER;
    }

    public static class SupplierAttachment {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "suppliers-attachment";
        public static final String SUPPLIER_IDENTIFIER = IDENTIFIER;
    }

    public static class SupplierChangeRequest {
        public static final String CHANGE_REQUEST = Supplier.ROOTPATH + SEPARATOR + "change-request";

        public static class SupplierIdCard {
            public static final String ROOTPATH = CHANGE_REQUEST + SEPARATOR + "id-card";
            public static final String SUPPLIER_ID_CARD_IDENTIFIER = IDENTIFIER;
            public static final String SUPPLIER_ID_CARD_SUBMIT = SUPPLIER_ID_CARD_IDENTIFIER + SEPARATOR + "submit";
            public static final String SUPPLIER_OWN_ORGANIZATION_APPLICANT_PERSON = "organization-applicant-list";
            public static final String SUPPLIER_OWN_ORGANIZATION_APPLICANT_PERSON_ID = "organization-applicant-list/{id}/{idCardType}";
        }
    }

    public static class UpdatedDocument {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "suppliers-updated-document";
        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String CURRENT = "current";

    }

    public static class SupplierIdCard {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-id-card";
        public static final String SUPPLIER_ID_CARD_IDENTIFIER = IDENTIFIER;
        public static final String PRE_APPLICATION_IDENTIFIER = SEPARATOR + "pre-application";
    }

    public static class SupplierPreApplication {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "pre-application";
        public static final String SUPPLIER_PRE_APPLICATION_IDENTIFIER = IDENTIFIER;
    }

    public static class WithdrawSecurityDepositRequest {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "withdraw-security-deposit";
        public static final String WITHDRAW_SECURITY_DEPOSIT_IDENTIFIER = IDENTIFIER;
        public static final String WITHDRAW_SECURITY_DEPOSIT_UPDATE_STATUS = SEPARATOR+"update-status"+IDENTIFIER;
        public static final String WITHDRAW_SECURITY_DEPOSIT_ADD_DECISION = IDENTIFIER + SEPARATOR+"add-decision";

        public static final String WITHDRAW_SECURITY_DEPOSIT_REQUEST_SEMD_TO_OFFICES = IDENTIFIER + SEPARATOR+"request-send-to-offices";
    }

    public static class SupplierRegistration {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "suppliers/registration";
        public static final String REVALIDATE_SUPPLIER_IDENTIFIER = SEPARATOR + "revalidate-supplier";
        public static final String RESEND_OTP_IDENTIFIER = SEPARATOR + "resend-otp" + SEPARATOR + OPEN_PARENTHESIS + "token" + CLOSE_PARENTHESIS;
        public static final String VERIFY_OTP_IDENTIFIER = SEPARATOR + "verify-otp" + SEPARATOR + OPEN_PARENTHESIS + "token" + CLOSE_PARENTHESIS;
    }

    public static class SupplierBioData {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-biodatas";
        public static final String SUPPLIER_BIO_DATA_IDENTIFIER = IDENTIFIER;
        public static final String SUPPLIER_BIO_DATA_WITNESS = IDENTIFIER + SEPARATOR + "witness";
        public static final String SUPPLIER_BIO_DATA_PROFILE =  SEPARATOR + "owner-partner-representative"+SEPARATOR+"{profileId}";

    }

    public static class SupplierPrincipal {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-principals";
        public static final String SUPPLIER_PRINCIPAL_IDENTIFIER = IDENTIFIER;
    }
    public static class SupplierPunishmentType {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-punishment-types";
        public static final String SUPPLIER_PUNISHMENT_TYPE_IDENTIFIER = IDENTIFIER;
    }

    public static class SupplierPunishmentReason {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "punishment-reasons";
        public static final String SUPPLIER_PUNISHMENT_REASON_IDENTIFIER = IDENTIFIER;
    }
    public static class DisciplinaryAction {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-disciplinary-actions";
        public static final String SUPPLIER_DISCIPLINARY_ACTION_IDENTIFIER = IDENTIFIER;

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class SupplierBioDataWitness {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-biodata-witnesses";
        public static final String SUPPLIER_BIO_DATA_WITNESS_IDENTIFIER = IDENTIFIER;
        public static final String SUPPLIER_BIO_DATA_WITNESS_CURRENT = "{biodataId}" + SEPARATOR + "current";
        public static final String SUPPLIER_BIO_DATA_WITNESS_SAVE = "{biodataId}" + SEPARATOR + "save";
        public static final String SUPPLIER_BIO_DATA_WITNESS_UPDATE = "{biodataId}" + SEPARATOR + "update" + SEPARATOR + IDENTIFIER;
        public static final String SUPPLIER_BIO_DATA_WITNESS_SUBMIT = "{biodataId}" + SEPARATOR + "update" + SEPARATOR + IDENTIFIER + "submit";
    }

    public static class SupplierRepresentativeChange {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-representative-changes";
        public static final String SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER = IDENTIFIER;
    }

    public static class RepresentativeChangeApplication {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-representative-change-application";
        public static final String SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER = IDENTIFIER;
        public static final String SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER_BY_REPRESENTATIVE = IDENTIFIER + "/representative-current";

        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class RepresentativeChangeApplicationBioData {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-representative-change-application-bio-data";
        public static final String SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER = IDENTIFIER;
        public static final String SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER_BY_REPRESENTATIVE = IDENTIFIER + "/representative-current-bio-data";
    }

    public static class SupplierOwnerInfoChangeRequest {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-owner-info-change-requests";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_IDENTIFIER = IDENTIFIER;
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_SAVE_ORGANIZATION = IDENTIFIER + SEPARATOR + "save" + SEPARATOR + "organization";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_SAVE_OWNER_INFO = IDENTIFIER + SEPARATOR + "save" + SEPARATOR + "owner-info";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_SAVE_FIRST_PART = IDENTIFIER + SEPARATOR + "save" + SEPARATOR + "first";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_SAVE_SECOND_PART = IDENTIFIER + SEPARATOR + "save" + SEPARATOR + "second";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_SAVE_THIRD_PART = IDENTIFIER + SEPARATOR + "save" + SEPARATOR + "third";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_SAVE_FINAL_PART = IDENTIFIER + SEPARATOR + "save" + SEPARATOR + "final";
        public static final String SUPPLIER_OWNER_INFO_CHANGE_REQUEST_CURRENT = "current";
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class SupplierOrganizationAddressChangeRequest {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-organization-address-change-requests";
        public static final String SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_REQUEST_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
        public static final String SUPPLIER_ORG_ADDRESS_CHANGE_REQUEST_CURRENT = "current";
    }

    public static class RenewalSupplier {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "renewal-supplier-registration";
        public static final String CURRENT = "current";

        public static final String CURRENT_FISCAL_YEAR_INFO = CURRENT + SEPARATOR+ "fiscal-year-info";
        public static final String CURRENT_SUBMIT = CURRENT + SEPARATOR + "submit";

        public static final String APPLICATION_IDENTIFIER = IDENTIFIER;
        public static final String ADMIN_UPDATE_STATUS = APPLICATION_IDENTIFIER + SEPARATOR + "admin-update-status";

        public static final String UPDATED_DOCUMENT = APPLICATION_IDENTIFIER + SEPARATOR + "suppliers-updated-document";
        public static final String UPDATED_DOCUMENT_CURRENT = UPDATED_DOCUMENT + SEPARATOR + "current";
        public static final String UPDATED_DOCUMENT_UPDATE = UPDATED_DOCUMENT + SEPARATOR + OPEN_PARENTHESIS + "updatedDocumentId" + CLOSE_PARENTHESIS;



    }

    public static class SupplierOwnerChange {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-owner-change";
        public static final String CURRENT = "current";
        public static class SupplierOwnerChangeFirstPhase {
            public static final String ROOTPATH = SupplierOwnerChange.ROOTPATH + SEPARATOR + SupplierOwnerChange.CURRENT + SEPARATOR + "first-phase";
            public static final String CURRENT = "current";
            public static final String CURRENT_FIRST_PHASE_SUBMIT = CURRENT + SEPARATOR + "submit";
        }

        public static class SupplierOwnerChangeSecondPhase {
            public static final String ROOTPATH = SupplierOwnerChange.ROOTPATH + SEPARATOR + SupplierOwnerChange.CURRENT + SEPARATOR + "second-phase";
            public static final String CURRENT = "current";
            public static final String CURRENT_SECOND_PHASE_SUBMIT = CURRENT + SEPARATOR + "submit";
        }

    }

    public static class SupplierOwnerChangeFirstPhase {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-owner-change-first-phase";
        public static final String CURRENT = "current";
    }

    public static class SupplierCertificate {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-certificates";
        public static final String SUPPLIER_CERTIFICATE_IDENTIFIER = IDENTIFIER;
    }

    public static class SupplierCertificateTemplate {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-certificate-templates";
        public static final String SUPPLIER_CERTIFICATE_TEMPLATE_IDENTIFIER = IDENTIFIER;
    }

    public static class SupplierBioDataLawsuit {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "supplier-biodata-lawsuits";
        public static final String SUPPLIER_BIO_DATA_LAWSUIT_BY_BIO_DATA =  SEPARATOR + "biodata"+SEPARATOR+"{biodataId}";
        public static final String SUPPLIER_BIO_DATA_LAWSUIT_IDENTIFIER = IDENTIFIER;
    }

    /*********************** utility-service *****************/

    public static class Capture {
        public static final String ROOTPATH = BASEPATH + SEPARATOR + "captcha";
        public static final String VARIFY_CAPTURE_IDENTIFIER = "verify-captcha" + SEPARATOR + OPEN_PARENTHESIS + "captchaId" + CLOSE_PARENTHESIS + SEPARATOR + OPEN_PARENTHESIS + "captcha" + CLOSE_PARENTHESIS;
    }

    public static class CountryCategory {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "country-categories";
        public static final String COUNTRY_CATEGORY_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Country {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "countries";
        public static final String COUNTRY_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Currency {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "currencies";
        public static final String CURRENCY_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class EmailConfig {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "email-configs";
        public static final String CURRENCY_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = "{username}" + SEPARATOR + "update-status";
    }

    public static class Otp {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "otp";
        public static final String OTP_IDENTIFIER = IDENTIFIER;
        public static final String VERIFY_OTP_IDENTIFIER = "verify-otp" + SEPARATOR + OPEN_PARENTHESIS + "userId" + CLOSE_PARENTHESIS;
    }

    public static class SecretQuestion {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "secret-questions";
        public static final String SECRET_QUESTION_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class SecretQuestionForOpen {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "secret-questions-open";
        public static final String SECRET_QUESTION_FOR_OEPN_IDENTIFIER = IDENTIFIER;
    }

    public static class District {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "district";
        public static final String DISTRICT_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class InstanceTracker {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "instance-tracker";
        public static final String INSTANCE_TRACKER_IDENTIFIER = IDENTIFIER;
    }

    public static class Division {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "division";
        public static final String DIVISION_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class PoliceStation {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "police-station";
        public static final String POLICE_STATION_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    public static class Banks {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "banks";
        public static final String BANKS_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    /*********************** workflow-service *****************/

    public static class Workflow {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "workflow";
        public static final String INSTANCE_IDENTIFIER = "/{instanceId}";
        public static final String MAIN_SEEN_NOTESHEET = "main-seen-notesheet" + INSTANCE_IDENTIFIER;
        public static final String LETTER_IDENTIFIER = "/{letterId}";
        public static final String NOTEMASTER_IDENTIFIER = "/{noteMasterId}";
        public static final String EVENT_IDENTIFIER = "/{eventName}";
        public static final String LETTER_SEEN_NOTESHEET = "letter-seen-notesheet" + INSTANCE_IDENTIFIER + LETTER_IDENTIFIER;
        public static final String APPROVAL_PROCESSING_DETAILS = "approval-processing-details" + INSTANCE_IDENTIFIER + EVENT_IDENTIFIER;
        public static final String APPROVAL_PROCESSING_LETTER_DETAILS = "approval-processing-letter-details" + LETTER_IDENTIFIER + INSTANCE_IDENTIFIER + EVENT_IDENTIFIER;
        public static final String APPROVAL_PROCESSING_NOTEMASTER_DETAILS = "approval-processing-notemaster-details" + NOTEMASTER_IDENTIFIER + INSTANCE_IDENTIFIER + EVENT_IDENTIFIER;
        public static final String APPROVAL_PROCESSING_NOTEMASTER_AND_LETTERS_BY_ORDER = "approval-processing-notemasters-and-letters-by-order" + INSTANCE_IDENTIFIER;
        public static final String APPROVAL_PROCESSING_NOTEMASTERS_BY_LETTER_BY_ORDER = "approval-processing-notemasters-by-letter-by-order" + LETTER_IDENTIFIER;
    }

    public static class ApprovalProcessNoteMaster {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-process-note-master";
        public static final String APPROVAL_PROCESS_NOTE_MASTER_IDENTIFIER = IDENTIFIER;
    }

    public static class ApprovalProcess {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-processes";
        public static final String APPROVAL_PROCESS_IDENTIFIER = IDENTIFIER;
    }

    public static class ApprovalProcessHierarchy {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-processes-hierarchy";
        public static final String APPROVAL_PROCESS_HIERARCHY_IDENTIFIER = IDENTIFIER;
        public static final String SAVE_MULTIPLE_IDENTIFIER = SEPARATOR + "save-multiple";
    }

    public static class ApprovalProcessLetter {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-processes-letter";
        public static final String APPROVAL_PROCESS_LETTER_IDENTIFIER = IDENTIFIER;
        public static final String SAVE_MULTIPLE_IDENTIFIER = SEPARATOR + "save-multiple";
        public static final String APPROVAL_PROCESS_LETTER_FORWARD = IDENTIFIER + SEPARATOR + "forward";
        public static final String APPROVAL_PROCESS_LETTER_BY_INSTANCE_ID =  "instance"+SEPARATOR+"{instanceId}";
        public static final String APPROVAL_PROCESS_LETTER_FORWARD_DGFI = IDENTIFIER + SEPARATOR + "dgfi" + SEPARATOR + "forward";
    }

    public static class ApprovalProcessNote {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-process-notes";
        public static final String SAVE_MULTIPLE_IDENTIFIER = SEPARATOR  + "multiple";
        public static final String APPROVAL_PROCESS_LETTER_IDENTIFIER = IDENTIFIER;/*
        public static final String SAVE_MULTIPLE_IDENTIFIER = SEPARATOR + "save-multiple";
        public static final String APPROVAL_PROCESS_LETTER_FORWARD = IDENTIFIER + SEPARATOR + "forward";
        public static final String APPROVAL_PROCESS_LETTER_FORWARD_DGFI = IDENTIFIER + SEPARATOR + "dgfi" + SEPARATOR + "forward";*/
    }

    public static class ApprovalProcessHierarchyMaster {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "approval-processes-hierarchy-master";
        public static final String APPROVAL_PROCESS_HIERARCHY_MASTER_IDENTIFIER = IDENTIFIER;
        public static final String APPROVAL_PROCESS_HIERARCHY_MASTER_APPROVERS = SEPARATOR + "approvers";
    }

    public static class GeneralLetter {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "general-letters";
        public static final String UPDATE_READ_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-read-status";
        public static final String GENERAL_LETTER_IDENTIFIER = IDENTIFIER;
    }

    /*********************** configuration-service *****************/

    public static class GeneralConfig {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "general-configs";
        public static final String GENERAL_CONFIG_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-status";
    }

    /*********************** payment-service *****************/
    public static class Payment {
        public static final String PAYMENT_SERVICE_PATH = "payment";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "payment";
        public static final String PAYMENT_IDENTIFIER = IDENTIFIER;
    }

    public static class BankPayment {
        public static final String BANK_PAYMENT_PATH = "bank-payment";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + BANK_PAYMENT_PATH;
        public static final String BANK_PAYMENT_IDENTIFIER = IDENTIFIER;
    }

    public static class BankPaymentMode {
        public static final String BANK_PAYMENT_MODE_PATH = "bank-payment-mode";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + BANK_PAYMENT_MODE_PATH;
        public static final String BANK_PAYMENT_MODE_IDENTIFIER = IDENTIFIER;
    }

    /*********************** invoice-service *****************/
    public static class Invoice {
        public static final String INVOICE_PATH = "invoice";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + INVOICE_PATH;
        public static final String INVOICE_IDENTIFIER = IDENTIFIER;
    }

    public static class InvoiceSSLPayment {
        public static final String INVOICE_SSL_PAYMENT_PATH = Invoice.INVOICE_PATH + SEPARATOR + "ssl-payment";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + OPENBASE_PATH + SEPARATOR + INVOICE_SSL_PAYMENT_PATH;
        public static final String INVOICE_IDENTIFIER = IDENTIFIER;
        public static final String INVOICE_SSL_PAYMENT_INIT = SEPARATOR + "init";
        public static final String INVOICE_SSL_PAYMENT_INIT_1 = SEPARATOR + "init-1";
        public static final String INVOICE_SSL_PAYMENT_SUCCESS = SEPARATOR + "success";
        public static final String INVOICE_SSL_PAYMENT_FAIL = SEPARATOR + "fail";
        public static final String INVOICE_SSL_PAYMENT_CANCEL = SEPARATOR + "cancel";
        public static final String INVOICE_SSL_PAYMENT_IPN = SEPARATOR + "ipn";
    }

    /*********************** tender-service *****************/
    public static class TenderSchedule {
        public static final String TENDER_SCHEDULE_PATH = "tender-schedules";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + TENDER_SCHEDULE_PATH;
        public static final String TENDER_SCHEDULE_IDENTIFIER = IDENTIFIER;
    }

    public static class TenderInvitationRoute {
        public static final String TENDER_SCHEDULE_PATH = "tender-invitations";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + TENDER_SCHEDULE_PATH;
        public static final String TENDER_SCHEDULE_IDENTIFIER = IDENTIFIER;
    }

    public static class TenderInstructionDPMaster {
        public static final String TENDER_INSTRUCTION_MASTER_PATH = "tender-invitation-master";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + TENDER_INSTRUCTION_MASTER_PATH;
        public static final String SAVE_DP1_MASTER = SEPARATOR + "save" + SEPARATOR + "dp1" + SEPARATOR + "master";
        public static final String SAVE_DP2_MASTER = SEPARATOR + "save" + SEPARATOR + "dp2" + SEPARATOR + "master";
        public static final String GET_DP1_MASTER = SEPARATOR + "dp1" + SEPARATOR + "master";
        public static final String GET_DP2_MASTER = SEPARATOR + "dp2" + SEPARATOR + "master";
        public static final String TENDER_SCHEDULE_IDENTIFIER = IDENTIFIER;
    }

    public static class TenderInstruction {
        public static final String TENDER_INSTRUCTION_PATH = "tender-instructions";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + TENDER_INSTRUCTION_PATH;
        public static final String TENDER_INSTRUCTION_IDENTIFIER = IDENTIFIER;
        public static final String TENDER_INSTRUCTION_IDENTIFIER_SCHEDULE_ID = SEPARATOR + "tender-schedules"+SEPARATOR+"{id}";
        public static final String TENDER_INSTRUCTION_IDENTIFIER_DP1 = SEPARATOR + "dp1";
        public static final String TENDER_INSTRUCTION_IDENTIFIER_DP2 = SEPARATOR + "dp2";
        public static final String TENDER_INSTRUCTION_IDENTIFIER_DP4_1 = SEPARATOR + "dp4_1";
        public static final String TENDER_INSTRUCTION_IDENTIFIER_DP4_2 = SEPARATOR + "dp4_2";
        public static final String TENDER_INSTRUCTION_IDENTIFIER_DP5 = SEPARATOR + "dp5";
        public static final String TENDER_INSTRUCTION_IDENTIFIER_DP6 = SEPARATOR + "dp6";
    }


    /*********************** notification-service *****************/
    public static class Notification {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "notifications";
        public static final String APPOINTMENT_IDENTIFIER = IDENTIFIER;
        public static final String UPDATE_READ_STATUS_IDENTIFIER = IDENTIFIER + SEPARATOR + "update-read-status";
        public static final String INSTANCE_DETAILS = IDENTIFIER + SEPARATOR + "all-notifications-instance-details";
    }

    /*********************** notification-broadcasting-service *****************/
    public static class NotificationBroadcasting {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "notifications-broadcasting";
        public static final String APPOINTMENT_IDENTIFIER = IDENTIFIER;
        public static final String SENDER_LIST = APPOINTMENT_IDENTIFIER + SEPARATOR + "sender";
        public static final String SENDER_GROUP = APPOINTMENT_IDENTIFIER + SEPARATOR + "sender-group";
        public static final String SUBMIT = APPOINTMENT_IDENTIFIER + SEPARATOR + "submit";
        public static final String SENDER_LIST_ALL = APPOINTMENT_IDENTIFIER + SEPARATOR + "sender-all";
    }
    public static class ChatMessage {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "chat-messages";
        public static final String CHAT_MESSAGE_IDENTIFIER = IDENTIFIER;
        public static final String CHAT_REGISTRATION = SEPARATOR + "chat.registration";
        public static final String CHAT_TO_PERSONAL = SEPARATOR + "chat.send.personal";
        public static final String CHAT_TO_GROUP = SEPARATOR + "chat" + SEPARATOR + OPEN_PARENTHESIS + "roomId" + CLOSE_PARENTHESIS;
        public static final String ONE_TO_ONE_CHAT_PATH = SEPARATOR + "queue" + SEPARATOR + "messages";

        public static final String GROUP_TO_ONE_CHAT_PATH = SEPARATOR + "topic" + SEPARATOR;

        public static final String ALL_ONLINE_CLIENT = SEPARATOR + "online" + SEPARATOR + "clients";
        public static final String CHECK_ONLINE_CLIENT = SEPARATOR + "online" + SEPARATOR + "check" + SEPARATOR + OPEN_PARENTHESIS + "username" + CLOSE_PARENTHESIS;
        public static final String CHAT_BY_SENDER = SEPARATOR + "chat" + SEPARATOR  + OPEN_PARENTHESIS + "senderUserID" + CLOSE_PARENTHESIS;
        public static final String ONE_TO_ONE_CHAT_HISTORY = SEPARATOR + "chat" + SEPARATOR  + OPEN_PARENTHESIS + "currentUserID" + CLOSE_PARENTHESIS + SEPARATOR + OPEN_PARENTHESIS + "recipientUserId" + CLOSE_PARENTHESIS;
    }

    public static class ChatGroup {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "chat-groups";
        public static final String CHAT_GROUP_IDENTIFIER = IDENTIFIER;
    }

    public static class WebSocket {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "web-sockets";
        public static final String WEV_SOCKET_IDENTIFIER = IDENTIFIER;
        public static final String ONE_TO_ONE_CHAT_PATH = SEPARATOR + "queue" + SEPARATOR + "messages";
        public static final String CHAT_TO_PERSONAL = SEPARATOR + "chat";
        public static final String CHAT_TO_GROUP = SEPARATOR + "chat" + SEPARATOR + OPEN_PARENTHESIS + "groupId" + CLOSE_PARENTHESIS;
    }

    public static class SupplierPreApplicationImport{
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "import";
        public static final String IMPORT_PRE_APPLICATION_STEP_3 = "/supplier/import-pre-application";
        public static final String IMPORT_PRE_APPLICATION_REGISTER_SUPPLIER_STEP_2 = "/supplier/import-pre-application/register-supplier";
        public static final String IMPORT_PRE_APPLICATION_STEP_1 ="/import-pre-application";
    }

    public static class SupplierMainApplicationImport {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "import-main-application";
        public static final String IMPORT_MAIN_APPLICATION_IMPORT_OWNER = "/supplier/import-main-application/import-owner";
//
        public static final String IMPORT_MAIN_APPLICATION_IMPORT_ORGANIZATION = "/supplier/import-main-application/import-organization";
        public static final String IMPORT_MAIN_APPLICATION_IMPORT_DGF = "/supplier/import-main-application/import-dgfi";
        public static final String IMPORT_MAIN_APPLICATION_IMPORT_BIODATA = "/supplier/import-main-application/import-biodata";
        public static final String IMPORT_MAIN_APPLICATION_ORGANIZATION = "/import-main-application-organization";
        public static final String IMPORT_MAIN_APPLICATION_OWNER = "/import-main-application-owner";
        public static final String IMPORT_MAIN_APPLICATION_DGFI = "/import-main-application-dgfi";
        public static final String IMPORT_MAIN_APPLICATION_BIODATA = "/import-main-application-biodata";

    }

    public static class PaymentCalsRoute {
        public static final String PAYMENAT_CALCULATION_PATH = "payment-calculation";
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "payment" + SEPARATOR + PAYMENAT_CALCULATION_PATH;
    }

    public static class PriorAdminApprovalPath {
        public static final String ROOTPATH = BASEPATH + VERSION + SEPARATOR + "prior-admin-approval";
        public static final String CFA_DETAILS_IDENTIFIER = IDENTIFIER;
    }
}
