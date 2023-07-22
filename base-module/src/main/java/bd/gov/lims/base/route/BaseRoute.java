package bd.gov.lims.base.route;

import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseRoute {

    public static final String OPEN_PATH = "/**";
    public static final List<Route> routes = new ArrayList<>();
    public static final AntPathMatcher matcher = new AntPathMatcher();
    static {
        routes.add(PublicRoute.builder().path(ApiProvider.LOGIN_PATH).method("POST").build());
        routes.add(PublicRoute.builder().path(ApiProvider.OPEN_PATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SWAGGER_PATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SWAGGER_PATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SWAGGER_LIMS_PATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.API_DOCS_PATH).build());

        routes.add(PublicRoute.builder().path(ApiProvider.Capture.ROOTPATH + OPEN_PATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.Portal.ROOTPATH + OPEN_PATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH).method("GET").build());

        routes.add(PublicRoute.builder().path(ApiProvider.AdminForgotPassword.ROOTPATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.AdminForgotPassword.ROOTPATH + ApiProvider.AdminForgotPassword.RESET_PASSWORD_VERIFY_TOKEN).build());
        routes.add(PublicRoute.builder().path(ApiProvider.AdminForgotPassword.ROOTPATH + ApiProvider.AdminForgotPassword.RESET_PASSWORD_NEW_PASSWORD_AND_VERIFY_OTP).build());

        routes.add(PublicRoute.builder().path(ApiProvider.Otp.ROOTPATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.Otp.ROOTPATH + ApiProvider.Otp.VERIFY_OTP_IDENTIFIER).build());

        routes.add(PublicRoute.builder().path(ApiProvider.InvoiceSSLPayment.ROOTPATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.InvoiceSSLPayment.ROOTPATH + ApiProvider.InvoiceSSLPayment.INVOICE_SSL_PAYMENT_INIT).build());
        routes.add(PublicRoute.builder().path(ApiProvider.InvoiceSSLPayment.ROOTPATH + ApiProvider.InvoiceSSLPayment.INVOICE_SSL_PAYMENT_SUCCESS).build());

        routes.add(PublicRoute.builder().path(ApiProvider.SupplierPreApplicationImport.ROOTPATH).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SupplierPreApplicationImport.ROOTPATH + ApiProvider.SupplierPreApplicationImport.IMPORT_PRE_APPLICATION_STEP_1).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SupplierPreApplicationImport.ROOTPATH + ApiProvider.SupplierPreApplicationImport.IMPORT_PRE_APPLICATION_REGISTER_SUPPLIER_STEP_2).build());
        routes.add(PublicRoute.builder().path(ApiProvider.SupplierPreApplicationImport.ROOTPATH + ApiProvider.SupplierPreApplicationImport.IMPORT_PRE_APPLICATION_STEP_3).build());


        /*********************** Secured routes ****************************/

        routes.add(SecureRoute.builder().path(ApiProvider.AppUserAssignRole.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_ASSIGN_ROLE_CREATE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH + ApiProvider.AppUser.ME_INFO).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_VIEW_ME"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH + ApiProvider.AppUser.USER_LOCK).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_LOCK"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.AppUser.ROOTPATH + ApiProvider.AppUser.OFFICER).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APP_USER_CREATE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ForgetPassword.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PASSWORD_RESET_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ForgetPassword.RESET_PASSWORD_VERIFY_OTP).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RESET_PASSWORD_VERIFY_OTP_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ForgetPassword.NEW_PASSWORD).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NEW_PASSWORD_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ForgetPassword.RESET_PASSWORD_RESEND_OTP).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RESET_PASSWORD_RESEND_OTP_CREATE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Privilege.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIVILEGE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Privilege.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIVILEGE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Privilege.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIVILEGE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Privilege.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIVILEGE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Privilege.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIVILEGE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Role.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ROLE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Role.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ROLE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Role.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ROLE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Role.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ROLE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Role.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ROLE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Role.ROOTPATH + ApiProvider.Role.ASSIGN_PRIVILEGE).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ASSIGN_PRIVILEGE_UPDATE"}).build());

        /*********************** organization-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.AppointmentActivityRole.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_ACTIVITY_ROLE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppointmentActivityRole.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_ACTIVITY_ROLE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppointmentActivityRole.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_ACTIVITY_ROLE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppointmentActivityRole.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_ACTIVITY_ROLE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.AppointmentActivityRole.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_ACTIVITY_ROLE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Appointment.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Appointment.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Appointment.ROOTPATH + ApiProvider.Appointment.APPOINTMENT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Appointment.ROOTPATH + ApiProvider.Appointment.APPOINTMENT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Appointment.ROOTPATH + ApiProvider.Appointment.APPOINTMENT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Appointment.ROOTPATH + ApiProvider.Appointment.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_STATUS"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.Designation.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DESIGNATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Designation.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DESIGNATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Designation.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DESIGNATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Designation.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DESIGNATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Designation.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DESIGNATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.MilitaryRank.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MILITARY_RANK_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.MilitaryRank.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MILITARY_RANK_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.MilitaryRank.ROOTPATH + ApiProvider.MilitaryRank.MILITARY_RANK_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MILITARY_RANK_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.MilitaryRank.ROOTPATH + ApiProvider.MilitaryRank.MILITARY_RANK_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MILITARY_RANK_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.MilitaryRank.ROOTPATH + ApiProvider.MilitaryRank.MILITARY_RANK_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MILITARY_RANK_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.MilitaryRank.ROOTPATH + ApiProvider.MilitaryRank.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MILITARY_RANK_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.OfficeCategories.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CATEGORY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeCategories.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CATEGORY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeCategories.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CATEGORY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeCategories.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CATEGORY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeCategories.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CATEGORY_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeCategories.ROOTPATH + ApiProvider.OfficeCategories.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CATEGORY_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Office.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Office.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Office.ROOTPATH + ApiProvider.Office.OFFICE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Office.ROOTPATH + ApiProvider.Office.OFFICE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Office.ROOTPATH + ApiProvider.Office.OFFICE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Office.ROOTPATH + ApiProvider.Office.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.OfficeLevel.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LEVEL_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeLevel.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LEVEL_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeLevel.ROOTPATH + ApiProvider.OfficeLevel.OFFICE_LEVEL_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LEVEL_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeLevel.ROOTPATH + ApiProvider.OfficeLevel.OFFICE_LEVEL_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LEVEL_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeLevel.ROOTPATH + ApiProvider.OfficeLevel.OFFICE_LEVEL_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LEVEL_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeLevel.ROOTPATH + ApiProvider.OfficeLevel.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_LEVEL_STATUS"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.OfficeRole.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ROLE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeRole.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ROLE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeRole.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ROLE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeRole.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ROLE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeRole.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ROLE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeRole.ROOTPATH + ApiProvider.OfficeRole.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ROLE_STATUS"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.OfficeUser.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_USER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeUser.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_USER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeUser.ROOTPATH + ApiProvider.OfficeUser.OFFICE_USER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_USER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeUser.ROOTPATH + ApiProvider.OfficeUser.OFFICE_USER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_USER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeUser.ROOTPATH + ApiProvider.OfficeUser.OFFICE_USER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_USER_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeUser.ROOTPATH + ApiProvider.OfficeUser.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_USER_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.AppointmentHistory.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPOINTMENT_HISTORY_LIST"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Board.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Board.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Board.ROOTPATH + ApiProvider.Board.BOARD_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Board.ROOTPATH + ApiProvider.Board.BOARD_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Board.ROOTPATH + ApiProvider.Board.BOARD_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Board.ROOTPATH + ApiProvider.Board.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.BoardMemberPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BoardMemberPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BoardMemberPath.ROOTPATH + ApiProvider.BoardMemberPath.BOARD_MEMBER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_MEMBER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BoardMemberPath.ROOTPATH + ApiProvider.BoardMemberPath.BOARD_MEMBER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_MEMBER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BoardMemberPath.ROOTPATH + ApiProvider.BoardMemberPath.BOARD_MEMBER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BOARD_MEMBER_DELETE"}).build());

        /*********************** i18n-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.Language.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LANGUAGE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Language.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LANGUAGE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Language.ROOTPATH + ApiProvider.Language.LANGUAGE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LANGUAGE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Language.ROOTPATH + ApiProvider.Language.LANGUAGE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LANGUAGE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Language.ROOTPATH + ApiProvider.Language.LANGUAGE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LANGUAGE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Language.ROOTPATH + ApiProvider.Language.LANGUAGE_REVERSE_ENGINEERING).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LANGUAGE_CREATE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Modules.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MODULE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Modules.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MODULE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Modules.ROOTPATH + ApiProvider.Modules.MODULE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MODULE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Modules.ROOTPATH + ApiProvider.Modules.MODULE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MODULE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Modules.ROOTPATH + ApiProvider.Modules.MODULE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MODULE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SubModules.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUB_MODULE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SubModules.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUB_MODULE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SubModules.ROOTPATH + ApiProvider.SubModules.SUB_MODULE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUB_MODULE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SubModules.ROOTPATH + ApiProvider.SubModules.SUB_MODULE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUB_MODULE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SubModules.ROOTPATH + ApiProvider.SubModules.SUB_MODULE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUB_MODULE_DELETE"}).build());

        /*********************** indent-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalPrinciple.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalPrinciple.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalPrinciple.ROOTPATH + ApiProvider.ApprovalPrinciple.APPROVAL_PRINCIPLE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalPrinciple.ROOTPATH + ApiProvider.ApprovalPrinciple.APPROVAL_PRINCIPLE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalPrinciple.ROOTPATH + ApiProvider.ApprovalPrinciple.APPROVAL_PRINCIPLE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalInPrincipleLetter.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_LETTER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalInPrincipleLetter.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_LETTER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalInPrincipleLetter.ROOTPATH + ApiProvider.ApprovalInPrincipleLetter.APPROVAL_IN_PRINCIPLE_LETTER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_LETTER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalInPrincipleLetter.ROOTPATH + ApiProvider.ApprovalInPrincipleLetter.APPROVAL_IN_PRINCIPLE_LETTER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_LETTER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalInPrincipleLetter.ROOTPATH + ApiProvider.ApprovalInPrincipleLetter.APPROVAL_IN_PRINCIPLE_LETTER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PRINCIPLE_LETTER_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH + ApiProvider.Indent.INDENT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH + ApiProvider.Indent.INDENT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH + ApiProvider.Indent.INDENT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH + ApiProvider.Indent.INDENT_TO_ADVANCED_INDENT).method("PATCH").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_TO_ADVANCE_INDENT"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH + ApiProvider.Indent.UPDATE_STATUS_IDENTIFIER).method("PATCH").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Indent.ROOTPATH + ApiProvider.Indent.FORWARDED_TO_INSPECTOR).method("PATCH").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"FORWARDED_TO_INSPECTOR"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemBrand.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_BRAND_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemBrand.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_BRAND_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemBrand.ROOTPATH + ApiProvider.ItemBrand.ITEM_BRAND_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_BRAND_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemBrand.ROOTPATH + ApiProvider.ItemBrand.ITEM_BRAND_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_BRAND_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemBrand.ROOTPATH + ApiProvider.ItemBrand.ITEM_BRAND_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_BRAND_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemCategory.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_CATEGORY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemCategory.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_CATEGORY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemCategory.ROOTPATH + ApiProvider.ItemCategory.ITEM_CATEGORY_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_CATEGORY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemCategory.ROOTPATH + ApiProvider.ItemCategory.ITEM_CATEGORY_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_CATEGORY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemCategory.ROOTPATH + ApiProvider.ItemCategory.ITEM_CATEGORY_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_CATEGORY_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Item.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Item.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Item.ROOTPATH + ApiProvider.Item.ITEM_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Item.ROOTPATH + ApiProvider.Item.ITEM_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Item.ROOTPATH + ApiProvider.Item.ITEM_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemModel.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_MODEL_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemModel.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_MODEL_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemModel.ROOTPATH + ApiProvider.ItemModel.ITEM_MODEL_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_MODEL_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemModel.ROOTPATH + ApiProvider.ItemModel.ITEM_MODEL_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_MODEL_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemModel.ROOTPATH + ApiProvider.ItemModel.ITEM_MODEL_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_MODEL_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemOrganization.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_ORGANIZATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemOrganization.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_ORGANIZATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemOrganization.ROOTPATH + ApiProvider.ItemOrganization.ITEM_ORGANIZATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_ORGANIZATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemOrganization.ROOTPATH + ApiProvider.ItemOrganization.ITEM_ORGANIZATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_ORGANIZATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemOrganization.ROOTPATH + ApiProvider.ItemOrganization.ITEM_ORGANIZATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_ORGANIZATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemUnit.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_UNIT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemUnit.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_UNIT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemUnit.ROOTPATH + ApiProvider.ItemUnit.ITEM_UNIT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_UNIT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemUnit.ROOTPATH + ApiProvider.ItemUnit.ITEM_UNIT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_UNIT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemUnit.ROOTPATH + ApiProvider.ItemUnit.ITEM_UNIT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_UNIT_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Specification.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Specification.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Specification.ROOTPATH + ApiProvider.Specification.SPECIFICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Specification.ROOTPATH + ApiProvider.Specification.SPECIFICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Specification.ROOTPATH + ApiProvider.Specification.SPECIFICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationTemplate.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TEMPLATE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationTemplate.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TEMPLATE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationTemplate.ROOTPATH + ApiProvider.SpecificationTemplate.SPECIFICATION_TEMPLATE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TEMPLATE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationTemplate.ROOTPATH + ApiProvider.SpecificationTemplate.SPECIFICATION_TEMPLATE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TEMPLATE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationTemplate.ROOTPATH + ApiProvider.SpecificationTemplate.SPECIFICATION_TEMPLATE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TEMPLATE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationType.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TYPE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationType.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TYPE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationType.ROOTPATH + ApiProvider.SpecificationType.SPECIFICATION_TYPE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TYPE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationType.ROOTPATH + ApiProvider.SpecificationType.SPECIFICATION_TYPE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TYPE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SpecificationType.ROOTPATH + ApiProvider.SpecificationType.SPECIFICATION_TYPE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SPECIFICATION_TYPE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemSpecification.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_SPECIFICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemSpecification.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_SPECIFICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemSpecification.ROOTPATH + ApiProvider.ItemSpecification.ITEM_SPECIFICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_SPECIFICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemSpecification.ROOTPATH + ApiProvider.ItemSpecification.ITEM_SPECIFICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_SPECIFICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemSpecification.ROOTPATH + ApiProvider.ItemSpecification.ITEM_SPECIFICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_SPECIFICATION_DELETE"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.IndentTraining.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_TRAINING_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentTraining.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_TRAINING_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentTraining.ROOTPATH + ApiProvider.IndentTraining.INDENT_TRAINING_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_TRAINING_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentTraining.ROOTPATH + ApiProvider.IndentTraining.INDENT_TRAINING_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_TRAINING_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentTraining.ROOTPATH + ApiProvider.IndentTraining.INDENT_TRAINING_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_TRAINING_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.IndentBookPublication.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_BOOK_PUBLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentBookPublication.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_BOOK_PUBLICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentBookPublication.ROOTPATH + ApiProvider.IndentBookPublication.INDENT_BOOK_PUBLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_BOOK_PUBLICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentBookPublication.ROOTPATH + ApiProvider.IndentBookPublication.INDENT_BOOK_PUBLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_BOOK_PUBLICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentBookPublication.ROOTPATH + ApiProvider.IndentBookPublication.INDENT_BOOK_PUBLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_BOOK_PUBLICATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.IndentAnnex.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_ANNEX_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentAnnex.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_ANNEX_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentAnnex.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_ANNEX_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentAnnex.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_ANNEX_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentAnnex.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_ANNEX_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentAnnex.ROOTPATH + ApiProvider.IndentAnnex.INDENT_ANNEX_UPLOAD_BY_FILE).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_ANNEX_UPLOAD_BY_FILE"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.IndentSstSsm.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_SST_SSM_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentSstSsm.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_SST_SSM_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentSstSsm.ROOTPATH + ApiProvider.IndentSstSsm.INDENT_SST_SSN_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_SST_SSM_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentSstSsm.ROOTPATH + ApiProvider.IndentSstSsm.INDENT_SST_SSN_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_SST_SSM_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.IndentSstSsm.ROOTPATH + ApiProvider.IndentSstSsm.INDENT_SST_SSN_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"INDENT_SST_SSM_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.BudgetCode.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BUDGET_CODE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BudgetCode.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BUDGET_CODE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BudgetCode.ROOTPATH + ApiProvider.BudgetCode.ITEM_BRAND_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BUDGET_CODE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BudgetCode.ROOTPATH + ApiProvider.BudgetCode.ITEM_BRAND_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BUDGET_CODE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BudgetCode.ROOTPATH + ApiProvider.BudgetCode.ITEM_BRAND_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BUDGET_CODE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BudgetCode.ROOTPATH + ApiProvider.BudgetCode.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BUDGET_CODE_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ItemStandardizationPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_STANDARDIZATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemStandardizationPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_STANDARDIZATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemStandardizationPath.ROOTPATH + ApiProvider.ItemStandardizationPath.ITEM_STANDARDIZATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_STANDARDIZATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemStandardizationPath.ROOTPATH + ApiProvider.ItemStandardizationPath.ITEM_STANDARDIZATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_STANDARDIZATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ItemStandardizationPath.ROOTPATH + ApiProvider.ItemStandardizationPath.ITEM_STANDARDIZATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"ITEM_STANDARDIZATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.OfficeIssues.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ISSUES_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeIssues.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ISSUES_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeIssues.ROOTPATH + ApiProvider.OfficeIssues.OFFICE_ISSUES_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ISSUES_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeIssues.ROOTPATH + ApiProvider.OfficeIssues.OFFICE_ISSUES_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ISSUES_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeIssues.ROOTPATH + ApiProvider.OfficeIssues.OFFICE_ISSUES_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ISSUES_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.OfficeIssues.ROOTPATH + ApiProvider.OfficeIssues.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"OFFICE_ISSUES_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PriorAdminApprovalPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIOR_ADMIN_APPROVAL_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PriorAdminApprovalPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIOR_ADMIN_APPROVAL_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PriorAdminApprovalPath.ROOTPATH + ApiProvider.PriorAdminApprovalPath.CFA_DETAILS_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIOR_ADMIN_APPROVAL_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PriorAdminApprovalPath.ROOTPATH + ApiProvider.PriorAdminApprovalPath.CFA_DETAILS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIOR_ADMIN_APPROVAL_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PriorAdminApprovalPath.ROOTPATH + ApiProvider.PriorAdminApprovalPath.CFA_DETAILS_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRIOR_ADMIN_APPROVAL_DELETE"}).build());

        /*********************** log-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.Log.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LOG_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Log.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LOG_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Log.ROOTPATH + ApiProvider.Log.LOG_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LOG_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Log.ROOTPATH + ApiProvider.Log.LOG_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LOG_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Log.ROOTPATH + ApiProvider.Log.LOG_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"LOG_DELETE"}).build());

        /*********************** portal-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.Miscellaneous.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MISCELLANEOUS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Miscellaneous.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MISCELLANEOUS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Miscellaneous.ROOTPATH + ApiProvider.Miscellaneous.MISCELLANEOUS_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MISCELLANEOUS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Miscellaneous.ROOTPATH + ApiProvider.Miscellaneous.MISCELLANEOUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MISCELLANEOUS_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Miscellaneous.ROOTPATH + ApiProvider.Miscellaneous.MISCELLANEOUS_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"MISCELLANEOUS_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.News.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NEWS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.News.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NEWS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.News.ROOTPATH + ApiProvider.News.NEWS_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NEWS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.News.ROOTPATH + ApiProvider.News.NEWS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NEWS_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.News.ROOTPATH + ApiProvider.News.NEWS_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NEWS_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Notice.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTICE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notice.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTICE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notice.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTICE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notice.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTICE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notice.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTICE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.StaticContent.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_CONTENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticContent.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_CONTENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticContent.ROOTPATH + ApiProvider.StaticContent.STATIC_CONTENT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_CONTENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticContent.ROOTPATH + ApiProvider.StaticContent.STATIC_CONTENT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_CONTENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticContent.ROOTPATH + ApiProvider.StaticContent.STATIC_CONTENT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_CONTENT_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.StaticFAQ.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_FAQ_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticFAQ.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_FAQ_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticFAQ.ROOTPATH + ApiProvider.StaticFAQ.STATIC_FAQ_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_FAQ_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticFAQ.ROOTPATH + ApiProvider.StaticFAQ.STATIC_FAQ_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_FAQ_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticFAQ.ROOTPATH + ApiProvider.StaticFAQ.STATIC_FAQ_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_FAQ_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.StaticGallery.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_GALLERY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticGallery.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_GALLERY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticGallery.ROOTPATH + ApiProvider.StaticGallery.STATIC_GALLERY_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_GALLERY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticGallery.ROOTPATH + ApiProvider.StaticGallery.STATIC_GALLERY_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_GALLERY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.StaticGallery.ROOTPATH + ApiProvider.StaticGallery.STATIC_GALLERY_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"STATIC_GALLERY_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ContactUs.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CONTACT_US_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ContactUs.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CONTACT_US_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ContactUs.ROOTPATH + ApiProvider.ContactUs.CONTRACT_US_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CONTACT_US_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ContactUs.ROOTPATH + ApiProvider.ContactUs.CONTRACT_US_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CONTACT_US_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ContactUs.ROOTPATH + ApiProvider.ContactUs.CONTRACT_US_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CONTACT_US_DELETE"}).build());

        /*********************** supplier-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationP2.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_P2_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationP2.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_P2_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationP2.ROOTPATH + ApiProvider.PostApplicationP2.POST_APPLICATION_P2_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_P2_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationP2.ROOTPATH + ApiProvider.PostApplicationP2.POST_APPLICATION_P2_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_P2_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationP2.ROOTPATH + ApiProvider.PostApplicationP2.POST_APPLICATION_P2_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_P2_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PreApplication.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplication.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplication.ROOTPATH + ApiProvider.PreApplication.PRE_APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplication.ROOTPATH + ApiProvider.PreApplication.PRE_APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplication.ROOTPATH + ApiProvider.PreApplication.PRE_APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PreApplicationMilitary.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_MILITARY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplicationMilitary.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_MILITARY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplicationMilitary.ROOTPATH + ApiProvider.PreApplicationMilitary.PRE_APPLICATION_MILITARY_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_MILITARY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplicationMilitary.ROOTPATH + ApiProvider.PreApplicationMilitary.PRE_APPLICATION_MILITARY_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_MILITARY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PreApplicationMilitary.ROOTPATH + ApiProvider.PreApplicationMilitary.PRE_APPLICATION_MILITARY_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PRE_APPLICATION_MILITARY_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Supplier.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Supplier.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Supplier.ROOTPATH + ApiProvider.Supplier.SUPPLIER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Supplier.ROOTPATH + ApiProvider.Supplier.SUPPLIER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Supplier.ROOTPATH + ApiProvider.Supplier.SUPPLIER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierChangeRequest.SupplierIdCard.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_REGISTERED"}).permissions(new String[]{"SUPPLIER_ID_CARD_LIST"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.SupplierChangeRequest.SupplierIdCard.ROOTPATH + ApiProvider.SEPARATOR + ApiProvider.SupplierChangeRequest.SupplierIdCard.SUPPLIER_OWN_ORGANIZATION_APPLICANT_PERSON).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_ROLE"}).permissions(new String[]{"SUPPLIER_ID_CARD_LIST"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.SupplierChangeRequest.SupplierIdCard.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_REGISTERED"}).permissions(new String[]{"SUPPLIER_ID_CARD_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierChangeRequest.SupplierIdCard.ROOTPATH + ApiProvider.SupplierChangeRequest.SupplierIdCard.SUPPLIER_ID_CARD_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_REGISTERED"}).permissions(new String[]{"SUPPLIER_ID_CARD_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierChangeRequest.SupplierIdCard.ROOTPATH + ApiProvider.SupplierChangeRequest.SupplierIdCard.SUPPLIER_ID_CARD_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_REGISTERED"}).permissions(new String[]{"SUPPLIER_ID_CARD_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierChangeRequest.SupplierIdCard.ROOTPATH + ApiProvider.SupplierChangeRequest.SupplierIdCard.SUPPLIER_ID_CARD_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_REGISTERED"}).permissions(new String[]{"SUPPLIER_ID_CARD_DELETE"}).build());

        //..
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATED_DOCUMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATED_DOCUMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATED_DOCUMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATED_DOCUMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATED_DOCUMENT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.CURRENT).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_UPDATED_DOCUMENT_CURRENT_LIST"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.ADMIN_UPDATE_STATUS).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.CURRENT).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_CURRENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.UPDATED_DOCUMENT_CURRENT).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_UPDATED_DOCUMENT_CURRENT"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalSupplier.ROOTPATH + ApiProvider.RenewalSupplier.UPDATED_DOCUMENT_UPDATE).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"RENEWAL_SUPPLIER_UPDATED_DOCUMENT_UPDATE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierAttachment.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ATTACHMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierAttachment.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ATTACHMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierAttachment.ROOTPATH + ApiProvider.SupplierAttachment.SUPPLIER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ATTACHMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierAttachment.ROOTPATH + ApiProvider.SupplierAttachment.SUPPLIER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ATTACHMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierAttachment.ROOTPATH + ApiProvider.SupplierAttachment.SUPPLIER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ATTACHMENT_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPayment.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PAYMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPayment.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PAYMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPayment.ROOTPATH + ApiProvider.SupplierPayment.SUPPLIER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PAYMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPayment.ROOTPATH + ApiProvider.SupplierPayment.SUPPLIER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PAYMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPayment.ROOTPATH + ApiProvider.SupplierPayment.SUPPLIER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PAYMENT_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplication.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplication.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplication.ROOTPATH + ApiProvider.RepresentativeChangeApplication.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplication.ROOTPATH + ApiProvider.RepresentativeChangeApplication.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplication.ROOTPATH + ApiProvider.RepresentativeChangeApplication.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplication.ROOTPATH + ApiProvider.RepresentativeChangeApplication.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.WithdrawSecurityDepositRequest.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"WITHDRAW_SECURITY_DEPOSIT_REQUEST_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.WithdrawSecurityDepositRequest.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"WITHDRAW_SECURITY_DEPOSIT_REQUEST_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.WithdrawSecurityDepositRequest.ROOTPATH + ApiProvider.WithdrawSecurityDepositRequest.WITHDRAW_SECURITY_DEPOSIT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"WITHDRAW_SECURITY_DEPOSIT_REQUEST_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.WithdrawSecurityDepositRequest.ROOTPATH + ApiProvider.WithdrawSecurityDepositRequest.WITHDRAW_SECURITY_DEPOSIT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"WITHDRAW_SECURITY_DEPOSIT_REQUEST_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.WithdrawSecurityDepositRequest.ROOTPATH + ApiProvider.WithdrawSecurityDepositRequest.WITHDRAW_SECURITY_DEPOSIT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"WITHDRAW_SECURITY_DEPOSIT_REQUEST_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.CURRENT_APPLICATION).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_CURRENT_APPLICATION_VIEW"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationAttachmentPath.CURRENT_APPLICATION).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_ATTACHMENT_CURRENT_APPLICATION_VIEW"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationPath.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationPath.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationPath.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationPath.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationPath.CURRENT_APPLICATION).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_CURRENT_APPLICATION_VIEW"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.ROOTPATH + ApiProvider.G4n10EnlistmentApplicationLatestAttachmentPath.CURRENT_APPLICATION).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_APPLICATION_LATEST_ATTACHMENT_CURRENT_VIEW"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH + ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH + ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH + ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH + ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.ROOTPATH + ApiProvider.G4n10EnlistmentPreApplicationForRetiredDefensePersonnelPath.CURRENT_APPLICATION).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"G4N10_ENLISTMENT_PRE_APPLICATION_FOR_RETIRED_DEFENSE_PERSONNEL_CURRENT_VIEW"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOwnerInformation.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_OWNER_INFORMATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOwnerInformation.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_OWNER_INFORMATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOwnerInformation.ROOTPATH + ApiProvider.PostApplicationOwnerInformation.POST_APPLICATION_OWNER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_OWNER_INFORMATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOwnerInformation.ROOTPATH + ApiProvider.PostApplicationOwnerInformation.POST_APPLICATION_OWNER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_OWNER_INFORMATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOwnerInformation.ROOTPATH + ApiProvider.PostApplicationOwnerInformation.POST_APPLICATION_OWNER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_OWNER_INFORMATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificate.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificate.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificate.ROOTPATH + ApiProvider.SupplierCertificate.SUPPLIER_CERTIFICATE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificate.ROOTPATH + ApiProvider.SupplierCertificate.SUPPLIER_CERTIFICATE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificate.ROOTPATH + ApiProvider.SupplierCertificate.SUPPLIER_CERTIFICATE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificateTemplate.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_TEMPLATE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificateTemplate.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_TEMPLATE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificateTemplate.ROOTPATH + ApiProvider.SupplierCertificateTemplate.SUPPLIER_CERTIFICATE_TEMPLATE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_TEMPLATE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificateTemplate.ROOTPATH + ApiProvider.SupplierCertificateTemplate.SUPPLIER_CERTIFICATE_TEMPLATE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_TEMPLATE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierCertificateTemplate.ROOTPATH + ApiProvider.SupplierCertificateTemplate.SUPPLIER_CERTIFICATE_TEMPLATE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_CERTIFICATE_TEMPLATE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.PostApplication.POST_APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.PostApplication.POST_APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.PostApplication.POST_APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplication.ROOTPATH + ApiProvider.PostApplication.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_VIEW_BY_APPLICATION_IDENTIFIER"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplicationBioData.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplicationBioData.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplicationBioData.ROOTPATH + ApiProvider.RepresentativeChangeApplicationBioData.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplicationBioData.ROOTPATH + ApiProvider.RepresentativeChangeApplicationBioData.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplicationBioData.ROOTPATH + ApiProvider.RepresentativeChangeApplicationBioData.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RepresentativeChangeApplicationBioData.ROOTPATH + ApiProvider.RepresentativeChangeApplicationBioData.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER_BY_REPRESENTATIVE).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_CHANGE_VIEW_BY_REPRESENTATIVE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.SEPARATOR + ApiProvider.SupplierChangeRequest.SupplierIdCard.SUPPLIER_OWN_ORGANIZATION_APPLICANT_PERSON).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE", "SUPPLIER_ROLE"}).permissions(new String[]{"SUPPLIER_ID_CARD_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ID_CARD_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.APPLICATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ID_CARD_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.APPLICATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ID_CARD_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.UpdatedDocument.ROOTPATH + ApiProvider.UpdatedDocument.APPLICATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ID_CARD_DELETE"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPreApplication.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRE_APPLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPreApplication.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRE_APPLICATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPreApplication.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRE_APPLICATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPreApplication.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRE_APPLICATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPreApplication.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRE_APPLICATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRegistration.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REGISTRATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRegistration.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REGISTRATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRegistration.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REGISTRATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRegistration.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REGISTRATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRegistration.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REGISTRATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioData.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioData.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioData.ROOTPATH + ApiProvider.SupplierBioData.SUPPLIER_BIO_DATA_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioData.ROOTPATH + ApiProvider.SupplierBioData.SUPPLIER_BIO_DATA_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioData.ROOTPATH + ApiProvider.SupplierBioData.SUPPLIER_BIO_DATA_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioDataWitness.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_WITNESS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioDataWitness.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_WITNESS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioDataWitness.ROOTPATH + ApiProvider.SupplierBioDataWitness.SUPPLIER_BIO_DATA_WITNESS_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_WITNESS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioDataWitness.ROOTPATH + ApiProvider.SupplierBioDataWitness.SUPPLIER_BIO_DATA_WITNESS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_WITNESS_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierBioDataWitness.ROOTPATH + ApiProvider.SupplierBioDataWitness.SUPPLIER_BIO_DATA_WITNESS_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_BIODATA_WITNESS_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRepresentativeChange.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_WITNESS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRepresentativeChange.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_WITNESS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRepresentativeChange.ROOTPATH + ApiProvider.SupplierRepresentativeChange.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_WITNESS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRepresentativeChange.ROOTPATH + ApiProvider.SupplierRepresentativeChange.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_WITNESS_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierRepresentativeChange.ROOTPATH + ApiProvider.SupplierRepresentativeChange.SUPPLIER_REPRESENTATIVE_CHANGE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_REPRESENTATIVE_WITNESS_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOwnerInfoChangeRequest.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_OWNER_INFO_CHANGE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOwnerInfoChangeRequest.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_OWNER_INFO_CHANGE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOwnerInfoChangeRequest.ROOTPATH + ApiProvider.SupplierOwnerInfoChangeRequest.SUPPLIER_OWNER_INFO_CHANGE_REQUEST_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_OWNER_INFO_CHANGE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOwnerInfoChangeRequest.ROOTPATH + ApiProvider.SupplierOwnerInfoChangeRequest.SUPPLIER_OWNER_INFO_CHANGE_REQUEST_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_OWNER_INFO_CHANGE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOwnerInfoChangeRequest.ROOTPATH + ApiProvider.SupplierOwnerInfoChangeRequest.SUPPLIER_OWNER_INFO_CHANGE_REQUEST_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_OWNER_INFO_CHANGE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOrganizationAddressChangeRequest.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOrganizationAddressChangeRequest.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOrganizationAddressChangeRequest.ROOTPATH + ApiProvider.SupplierOrganizationAddressChangeRequest.SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_REQUEST_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOrganizationAddressChangeRequest.ROOTPATH + ApiProvider.SupplierOrganizationAddressChangeRequest.SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_REQUEST_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierOrganizationAddressChangeRequest.ROOTPATH + ApiProvider.SupplierOrganizationAddressChangeRequest.SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_REQUEST_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_ORGANIZATION_ADDRESS_CHANGE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOrganization.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOrganization.ROOTPATH + ApiProvider.PostApplicationOrganization.POST_APPLICATION_SUPPLIER_CURRENT).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_LIST"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOrganization.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_CREATE"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOrganization.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_VIEW"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOrganization.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_UPDATE"}).build());
//        routes.add(SecureRoute.builder().path(ApiProvider.PostApplicationOrganization.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POST_APPLICATION_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPrincipal.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRINCIPAL_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPrincipal.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRINCIPAL_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPrincipal.ROOTPATH + ApiProvider.SupplierPrincipal.SUPPLIER_PRINCIPAL_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRINCIPAL_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPrincipal.ROOTPATH + ApiProvider.SupplierPrincipal.SUPPLIER_PRINCIPAL_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRINCIPAL_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPrincipal.ROOTPATH + ApiProvider.SupplierPrincipal.SUPPLIER_PRINCIPAL_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PRINCIPAL_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPunishmentType.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PUNISHMENT_TYPE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPunishmentType.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PUNISHMENT_TYPE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPunishmentType.ROOTPATH + ApiProvider.SupplierPunishmentType.SUPPLIER_PUNISHMENT_TYPE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PUNISHMENT_TYPE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPunishmentType.ROOTPATH + ApiProvider.SupplierPunishmentType.SUPPLIER_PUNISHMENT_TYPE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PUNISHMENT_TYPE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierPunishmentType.ROOTPATH + ApiProvider.SupplierPunishmentType.SUPPLIER_PUNISHMENT_TYPE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SUPPLIER_PUNISHMENT_TYPE_DELETE"}).build());


        /*********************** utility-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.CountryCategory.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CATEGORY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.CountryCategory.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CATEGORY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.CountryCategory.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CATEGORY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.CountryCategory.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CATEGORY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.CountryCategory.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CATEGORY_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH + ApiProvider.CountryCategory.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CATEGORY_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Country.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Country.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Country.ROOTPATH + ApiProvider.Country.COUNTRY_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Country.ROOTPATH + ApiProvider.Country.COUNTRY_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Country.ROOTPATH + ApiProvider.Country.COUNTRY_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Country.ROOTPATH + ApiProvider.Country.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"COUNTRY_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Currency.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CURRENCY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Currency.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CURRENCY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Currency.ROOTPATH + ApiProvider.Currency.CURRENCY_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CURRENCY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Currency.ROOTPATH + ApiProvider.Currency.CURRENCY_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CURRENCY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Currency.ROOTPATH + ApiProvider.Currency.CURRENCY_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CURRENCY_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Currency.ROOTPATH + ApiProvider.Currency.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CURRENCY_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH + ApiProvider.SecretQuestion.SECRET_QUESTION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH + ApiProvider.SecretQuestion.SECRET_QUESTION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH + ApiProvider.SecretQuestion.SECRET_QUESTION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestion.ROOTPATH + ApiProvider.SecretQuestion.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestionForOpen.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_FOR_OPEN_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestionForOpen.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_FOR_OPEN_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestionForOpen.ROOTPATH + ApiProvider.SecretQuestionForOpen.SECRET_QUESTION_FOR_OEPN_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_FOR_OPEN_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestionForOpen.ROOTPATH + ApiProvider.SecretQuestionForOpen.SECRET_QUESTION_FOR_OEPN_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_FOR_OPEN_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SecretQuestionForOpen.ROOTPATH + ApiProvider.SecretQuestionForOpen.SECRET_QUESTION_FOR_OEPN_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"SECRET_QUESTION_FOR_OPEN_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.District.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DISTRICT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.District.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DISTRICT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.District.ROOTPATH + ApiProvider.District.DISTRICT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DISTRICT_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.District.ROOTPATH + ApiProvider.District.DISTRICT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DISTRICT_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.District.ROOTPATH + ApiProvider.District.DISTRICT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DISTRICT_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.District.ROOTPATH + ApiProvider.District.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DISTRICT_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Division.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DIVISION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Division.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DIVISION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Division.ROOTPATH + ApiProvider.Division.DIVISION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DIVISION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Division.ROOTPATH + ApiProvider.Division.DIVISION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DIVISION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Division.ROOTPATH + ApiProvider.Division.DIVISION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DIVISION_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Division.ROOTPATH + ApiProvider.Division.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"DIVISION_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.PoliceStation.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POLICE_STATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PoliceStation.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POLICE_STATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PoliceStation.ROOTPATH + ApiProvider.PoliceStation.POLICE_STATION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POLICE_STATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PoliceStation.ROOTPATH + ApiProvider.PoliceStation.POLICE_STATION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POLICE_STATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PoliceStation.ROOTPATH + ApiProvider.PoliceStation.POLICE_STATION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POLICE_STATION_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.PoliceStation.ROOTPATH + ApiProvider.PoliceStation.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"POLICE_STATION_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BANKS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BANKS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH + ApiProvider.Banks.BANKS_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BANKS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH + ApiProvider.Banks.BANKS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BANKS_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH + ApiProvider.Banks.BANKS_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BANKS_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Banks.ROOTPATH + ApiProvider.Banks.UPDATE_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"BANKS_STATUS"}).build());

        /*********************** workflow-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcess.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcess.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcess.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcess.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcess.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchy.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchy.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchy.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchy.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchy.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchyMaster.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_MASTER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchyMaster.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_MASTER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchyMaster.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_MASTER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchyMaster.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_MASTER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessHierarchyMaster.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_HIERARCHY_MASTER_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH + ApiProvider.ApprovalProcessLetter.APPROVAL_PROCESS_LETTER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH + ApiProvider.ApprovalProcessLetter.APPROVAL_PROCESS_LETTER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH + ApiProvider.ApprovalProcessLetter.APPROVAL_PROCESS_LETTER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH + ApiProvider.ApprovalProcessLetter.APPROVAL_PROCESS_LETTER_FORWARD_DGFI).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_FORWARD_DGFI_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH + ApiProvider.ApprovalProcessLetter.APPROVAL_PROCESS_LETTER_FORWARD).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_FORWARD_STATUS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessLetter.ROOTPATH + ApiProvider.ApprovalProcessLetter.APPROVAL_PROCESS_LETTER_BY_INSTANCE_ID).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_LETTER_VIEW_BY_INSTANCE_ID"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNote.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNote.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNote.ROOTPATH + ApiProvider.ApprovalProcessNote.SAVE_MULTIPLE_IDENTIFIER).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_MULTIPLE_CREATES"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNote.ROOTPATH + ApiProvider.ApprovalProcessNote.APPROVAL_PROCESS_LETTER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNote.ROOTPATH + ApiProvider.ApprovalProcessNote.APPROVAL_PROCESS_LETTER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNote.ROOTPATH + ApiProvider.ApprovalProcessNote.APPROVAL_PROCESS_LETTER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.GeneralLetter.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_LETTER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralLetter.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_LETTER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralLetter.ROOTPATH + ApiProvider.GeneralLetter.GENERAL_LETTER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_LETTER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralLetter.ROOTPATH + ApiProvider.GeneralLetter.GENERAL_LETTER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_LETTER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralLetter.ROOTPATH + ApiProvider.GeneralLetter.GENERAL_LETTER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_LETTER_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralLetter.ROOTPATH + ApiProvider.GeneralLetter.UPDATE_READ_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_LETTER_STATUS"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNoteMaster.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_MASTER_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNoteMaster.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_MASTER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNoteMaster.ROOTPATH + ApiProvider.ApprovalProcessNoteMaster.APPROVAL_PROCESS_NOTE_MASTER_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_MASTER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNoteMaster.ROOTPATH + ApiProvider.ApprovalProcessNoteMaster.APPROVAL_PROCESS_NOTE_MASTER_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_MASTER_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ApprovalProcessNoteMaster.ROOTPATH + ApiProvider.ApprovalProcessNoteMaster.APPROVAL_PROCESS_NOTE_MASTER_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESS_NOTE_MASTER_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Workflow.ROOTPATH + ApiProvider.Workflow.APPROVAL_PROCESSING_DETAILS).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESSING_DETAILS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Workflow.ROOTPATH + ApiProvider.Workflow.APPROVAL_PROCESSING_LETTER_DETAILS).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESSING_LETTER_DETAILS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Workflow.ROOTPATH + ApiProvider.Workflow.APPROVAL_PROCESSING_NOTEMASTER_DETAILS).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESSING_NOTEMASTER_DETAILS_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Workflow.ROOTPATH + ApiProvider.Workflow.APPROVAL_PROCESSING_NOTEMASTER_AND_LETTERS_BY_ORDER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESSING_NOTEMASTER_AND_LETTERS_BY_ORDER_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Workflow.ROOTPATH + ApiProvider.Workflow.APPROVAL_PROCESSING_NOTEMASTERS_BY_LETTER_BY_ORDER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"APPROVAL_PROCESSING_NOTEMASTERS_BY_LETTER_BY_ORDER_VIEW"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_IMPORT_OWNER).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_IMPORT_OWNER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_IMPORT_ORGANIZATION).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_IMPORT_ORGANIZATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_IMPORT_DGF).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_IMPORT_DGF_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_IMPORT_BIODATA).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_IMPORT_BIODATA_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_ORGANIZATION).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_ORGANIZATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_OWNER).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_OWNER_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_DGFI).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_DGFI_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.SupplierMainApplicationImport.ROOTPATH + ApiProvider.SupplierMainApplicationImport.IMPORT_MAIN_APPLICATION_BIODATA).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"IMPORT_MAIN_APPLICATION_BIODATA_CREATE"}).build());

        
        /*********************** configuration-service *****************/


        routes.add(SecureRoute.builder().path(ApiProvider.GeneralConfig.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_CONFIGURATION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralConfig.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_CONFIGURATION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralConfig.ROOTPATH + ApiProvider.GeneralConfig.GENERAL_CONFIG_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_CONFIGURATION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralConfig.ROOTPATH + ApiProvider.GeneralConfig.GENERAL_CONFIG_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_CONFIGURATION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.GeneralConfig.ROOTPATH + ApiProvider.GeneralConfig.GENERAL_CONFIG_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"GENERAL_CONFIGURATION_DELETE"}).build());

        /*********************** tender-service *****************/


        routes.add(SecureRoute.builder().path(ApiProvider.TenderSchedule.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_SCHEDULE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderSchedule.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_SCHEDULE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderSchedule.ROOTPATH + ApiProvider.TenderSchedule.TENDER_SCHEDULE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_SCHEDULE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderSchedule.ROOTPATH + ApiProvider.TenderSchedule.TENDER_SCHEDULE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_SCHEDULE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderSchedule.ROOTPATH + ApiProvider.TenderSchedule.TENDER_SCHEDULE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_SCHEDULE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.TenderInstruction.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_INSTRUCTION_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderInstruction.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_INSTRUCTION_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderInstruction.ROOTPATH + ApiProvider.TenderInstruction.TENDER_INSTRUCTION_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_INSTRUCTION_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderInstruction.ROOTPATH + ApiProvider.TenderInstruction.TENDER_INSTRUCTION_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_INSTRUCTION_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.TenderInstruction.ROOTPATH + ApiProvider.TenderInstruction.TENDER_INSTRUCTION_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"TENDER_INSTRUCTION_DELETE"}).build());

        /*********************** payment-service *****************/

        /*********************** payment-invoice-service *****************/
        routes.add(SecureRoute.builder().path(ApiProvider.Invoice.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_INVOICE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Invoice.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_INVOICE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Invoice.ROOTPATH + ApiProvider.Invoice.INVOICE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_INVOICE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Invoice.ROOTPATH + ApiProvider.Invoice.INVOICE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_INVOICE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Invoice.ROOTPATH + ApiProvider.Invoice.INVOICE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_INVOICE_DELETE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.InvoiceSSLPayment.INVOICE_SSL_PAYMENT_INIT).method("DELETE").roles(new String[]{ }).permissions(new String[]{"INVOICE_SSL_PAYMENT_INIT"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.BankPayment.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPayment.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPayment.ROOTPATH + ApiProvider.BankPayment.BANK_PAYMENT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPayment.ROOTPATH + ApiProvider.BankPayment.BANK_PAYMENT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPayment.ROOTPATH + ApiProvider.BankPayment.BANK_PAYMENT_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.BankPaymentMode.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPaymentMode.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPaymentMode.ROOTPATH + ApiProvider.BankPaymentMode.BANK_PAYMENT_MODE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPaymentMode.ROOTPATH + ApiProvider.BankPaymentMode.BANK_PAYMENT_MODE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.BankPaymentMode.ROOTPATH + ApiProvider.BankPaymentMode.BANK_PAYMENT_MODE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_DELETE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.RenewalOfDGFIClearance.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalOfDGFIClearance.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalOfDGFIClearance.ROOTPATH + ApiProvider.RenewalOfDGFIClearance.RENEWAL_DGFI_CLEARANCE_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalOfDGFIClearance.ROOTPATH + ApiProvider.RenewalOfDGFIClearance.RENEWAL_DGFI_CLEARANCE_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.RenewalOfDGFIClearance.ROOTPATH + ApiProvider.RenewalOfDGFIClearance.RENEWAL_DGFI_CLEARANCE_IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"PAYMENT_BANK_MODE_DELETE"}).build());

        /*********************** notification-service *****************/

        routes.add(SecureRoute.builder().path(ApiProvider.ChatGroup.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CHAT_GROUP_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ChatGroup.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CHAT_GROUP_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ChatGroup.ROOTPATH + ApiProvider.IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CHAT_GROUP_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ChatGroup.ROOTPATH + ApiProvider.IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CHAT_GROUP_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.ChatGroup.ROOTPATH + ApiProvider.IDENTIFIER).method("DELETE").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"CHAT_GROUP_DELETE"}).build());


        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.APPOINTMENT_IDENTIFIER).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_VIEW"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.APPOINTMENT_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_UPDATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.SUBMIT).method("PATCH").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_SUBMIT"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.SENDER_LIST).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_RECIPIENT_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.SENDER_LIST).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_RECIPIENT_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.SENDER_GROUP).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_GROUP_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.NotificationBroadcasting.SENDER_GROUP).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_BROADCASTING_GROUP_CREATE"}).build());

        routes.add(SecureRoute.builder().path(ApiProvider.Notification.ROOTPATH).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_LIST"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notification.INSTANCE_DETAILS).method("GET").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_LIST_BY_INSTANCE_DETAILS"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notification.ROOTPATH).method("POST").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_CREATE"}).build());
        routes.add(SecureRoute.builder().path(ApiProvider.Notification.ROOTPATH + ApiProvider.Notification.UPDATE_READ_STATUS_IDENTIFIER).method("PUT").roles(new String[]{"SUPER_ADMIN_ROLE","ADMIN_ROLE"}).permissions(new String[]{"NOTIFICATIONS_READ_STATUS"}).build());
    }

    private static List<String> publicPaths = new ArrayList<>();

    public static String[] getPublicPaths() {
        if (publicPaths.isEmpty()) {
            publicPaths = routes.stream()
                    .filter(route -> route instanceof PublicRoute)
                    .map(route -> route.getPath())
                    .collect(Collectors.toList());
        }

        return publicPaths.stream()
                .toArray(String[]::new);
    }

    public static List<String> getPublicPathsList() {
        if (publicPaths.isEmpty()) {
            publicPaths = routes.stream()
                    .filter(route -> route instanceof PublicRoute)
                    .map(route -> route.getPath())
                    .collect(Collectors.toList());
        }

        return publicPaths;
    }

    private static String[] getPermissionOf(Module module) {
        return Permission.getPermissionAsStringArrayOf(module);
    }


    public static List<Route> securedPaths;

    public static List<Route> securedRoutes() {
        if (securedPaths.isEmpty()) {
            securedPaths = routes.stream()
                    .filter(route -> route instanceof SecureRoute)
                    .collect(Collectors.toList());
        }
        return securedPaths;
    }

    public static boolean matchPublicRouteByPathAndMethod(String path, String method) {
        String trimPath = trimServiceFromPath(path);
        Optional<Route> optionalRoute = routes.stream()
                .filter(route -> route instanceof PublicRoute
                        && matcher.match(route.getPath(), trimPath))
                .findFirst();
        if (!optionalRoute.isPresent()) return false;
        PublicRoute route = (PublicRoute) optionalRoute.get();
        if (route.getMethod() == null) return true;
        if (route.getMethod().equals(method)) return true;
        return false;
    }

    public static Optional<SecureRoute> findByPathAndMethod(String path, String method) {
        Optional<Route> optionalRoute = routes.stream()
                .filter(route -> matchPrivateRoute(route, path, method))
                .findFirst();
        if (optionalRoute.isPresent() && optionalRoute.get() instanceof SecureRoute) {
            return Optional.ofNullable((SecureRoute) optionalRoute.get());
        } else {
            return Optional.empty();
        }
    }

    public static boolean matchPrivateRoute(Route route, String path, String method) {
        return (route instanceof SecureRoute) &&
                matcher.match(route.getPath(), path) &&
                ((SecureRoute) route).getMethod().equals(method);
    }

    public static boolean matchPublicPath(String path) {
        String trimPath = trimServiceFromPath(path);
        String[] paths = getPublicPaths();
        for (String pattern : paths) {
            if (matcher.match(pattern, trimPath)) {
                return true;
            }
        }
        return false;
    }

    private static String trimServiceFromPath(String path) {
        if (!path.startsWith("/v3") && path.contains("v3/api-docs")) {
            path = path.substring(path.indexOf('/',1));
        }
        return path;
    }
}
