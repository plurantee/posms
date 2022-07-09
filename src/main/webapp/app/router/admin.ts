import { Authority } from '@/shared/security/authority';

const Register = () => import('@/account/register/register.vue');
const JhiUserManagementComponent = () => import('@/admin/user-management/user-management.vue');
const JhiUserManagementViewComponent = () => import('@/admin/user-management/user-management-view.vue');
const JhiUserManagementEditComponent = () => import('@/admin/user-management/user-management-edit.vue');

const ClientUserManagementComponent = () => import('@/admin/client-user-management/client-user-management.vue');
const ClientUserManagementViewComponent = () => import('@/admin/client-user-management/client-user-management-view.vue');
const ClientUserManagementEditComponent = () => import('@/admin/client-user-management/client-user-management-edit.vue');

const JhiDocsComponent = () => import('@/admin/docs/docs.vue');
const JhiConfigurationComponent = () => import('@/admin/configuration/configuration.vue');
const JhiHealthComponent = () => import('@/admin/health/health.vue');
const JhiLogsComponent = () => import('@/admin/logs/logs.vue');
const JhiMetricsComponent = () => import('@/admin/metrics/metrics.vue');

export default [
  {
    path: '/admin/register',
    name: 'Register',
    component: Register,
    meta: { authorities: [Authority.USER] },
  },
  {
    path: '/admin/user-management',
    name: 'JhiUser',
    component: JhiUserManagementComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/user-management/new',
    name: 'JhiUserCreate',
    component: JhiUserManagementEditComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/user-management/:userId/edit',
    name: 'JhiUserEdit',
    component: JhiUserManagementEditComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/user-management/:userId/view',
    name: 'JhiUserView',
    component: JhiUserManagementViewComponent,
    meta: { authorities: [Authority.ADMIN] },
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
  {
    path: '/admin/docs',
    name: 'JhiDocsComponent',
    component: JhiDocsComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/health',
    name: 'JhiHealthComponent',
    component: JhiHealthComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/logs',
    name: 'JhiLogsComponent',
    component: JhiLogsComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/metrics',
    name: 'JhiMetricsComponent',
    component: JhiMetricsComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
  {
    path: '/admin/configuration',
    name: 'JhiConfigurationComponent',
    component: JhiConfigurationComponent,
    meta: { authorities: [Authority.ADMIN] },
  },
];
