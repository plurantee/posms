import { Authority } from '@/shared/security/authority';

const Register = () => import('@/account/register/register.vue');
const Activate = () => import('@/account/activate/activate.vue');
const ResetPasswordInit = () => import('@/account/reset-password/init/reset-password-init.vue');
const ResetPasswordFinish = () => import('@/account/reset-password/finish/reset-password-finish.vue');
const ChangePassword = () => import('@/account/change-password/change-password.vue');
const Settings = () => import('@/account/settings/settings.vue');
const ClientUserManagementComponent = () => import('@/admin/client-user-management/client-user-management.vue');
const ClientUserManagementViewComponent = () => import('@/admin/client-user-management/client-user-management-view.vue');
const ClientUserManagementEditComponent = () => import('@/admin/client-user-management/client-user-management-edit.vue');

export default [
  {
    path: '/account/activate',
    name: 'Activate',
    component: Activate,
  },
  {
    path: '/account/reset/request',
    name: 'ResetPasswordInit',
    component: ResetPasswordInit,
  },
  {
    path: '/account/reset/finish',
    name: 'ResetPasswordFinish',
    component: ResetPasswordFinish,
  },
  {
    path: '/account/password',
    name: 'ChangePassword',
    component: ChangePassword,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/account/settings',
    name: 'Settings',
    component: Settings,
    meta: { authorities: [Authority.USER] },
  },
  // client admin
  {
    path: '/client-admin/register',
    name: 'Register',
    component: Register,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/client-admin/user-management',
    name: 'ClientUser',
    component: ClientUserManagementComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/client-admin/user-management/new',
    name: 'ClientUserCreate',
    component: ClientUserManagementEditComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/client-admin/user-management/:userId/edit',
    name: 'ClientUserEdit',
    component: ClientUserManagementEditComponent,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/client-admin/user-management/:userId/view',
    name: 'ClientUserView',
    component: ClientUserManagementViewComponent,
    meta: { authorities: [Authority.USER] },
  },
  //
];
