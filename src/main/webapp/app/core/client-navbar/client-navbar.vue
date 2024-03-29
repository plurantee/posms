<template>
  <b-navbar toggleable>
    <b-navbar-toggle v-b-toggle.sidebar-no-header>
      <template>
        <b-icon class="burger" icon="list"></b-icon>
      </template>
      <b-navbar-brand class="logo">
        <span class="logo-img"></span>
        <span class="navbar-title">FJ Online</span> <span class="navbar-version">{{ version }}</span>
      </b-navbar-brand>
    </b-navbar-toggle>
    <b-sidebar id="sidebar-no-header" aria-labelledby="sidebar-no-header-title" no-header shadow>
      <template #default="{ hide }">
        <b-button class="no-bg" variant="primary" block @click="hide">
          <b-navbar-brand class="logo">
            <span class="logo-img"></span>
            <span class="navbar-title">FJ Online</span>
            <b-icon icon="box-arrow-left"></b-icon>
          </b-navbar-brand>
        </b-button>
        <div class="p-3">
          <nav class="mb-3">
            <b-nav vertical>
              <b-nav-item to="/" exact>
                <span>
                  <font-awesome-icon icon="home" />
                  <span>Home</span>
                </span>
              </b-nav-item>
              <b-nav-item v-if="authenticated" to="/client/shop" exact>
                <span>
                  <b-icon icon="cart-check"></b-icon>
                  <span>Shops</span>
                </span>
              </b-nav-item>
              <b-nav-item v-if="authenticated" to="/client/order-tracker" exact>
                <span>
                  <b-icon icon="receipt"></b-icon>
                  <span>Order Tracker and Management</span>
                </span>
              </b-nav-item>
              <b-nav-item v-if="authenticated" to="/client/inventory" exact>
                <span>
                  <b-icon icon="house"></b-icon>
                  <span>Inventory Management</span>
                </span>
              </b-nav-item>
              <b-nav-item-dropdown
                right
                id="user-menu"
                v-if="authenticated"
                :class="{ 'router-link-active': subIsActive('/admin') }"
                active-class="active"
                class="pointer"
                data-cy="userMenu"
              >
                <span slot="button-content" class="navbar-dropdown-menu">
                  <font-awesome-icon icon="users-cog" />
                  <span class="no-bold">Client Admin</span>
                </span>
                <b-dropdown-item to="/client-admin/user-management" active-class="active">
                  <font-awesome-icon icon="users" />
                  <span>User management</span>
                </b-dropdown-item>
                <b-dropdown-item to="/client-admin/register" active-class="active">
                  <font-awesome-icon icon="users" />
                  <span>Register New User</span>
                </b-dropdown-item>
              </b-nav-item-dropdown>
              <b-nav-item-dropdown
                right
                id="entity-menu"
                v-if="hasAnyAuthority('ROLE_ADMIN') && authenticated"
                active-class="active"
                class="pointer"
                data-cy="entity"
              >
                <span slot="button-content" class="navbar-dropdown-menu">
                  <font-awesome-icon icon="th-list" />
                  <span class="no-bold">Entities</span>
                </span>
                <entities-menu></entities-menu>
                <!-- jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here -->
              </b-nav-item-dropdown>
              <b-nav-item-dropdown
                right
                id="admin-menu"
                v-if="hasAnyAuthority('ROLE_ADMIN') && authenticated"
                :class="{ 'router-link-active': subIsActive('/admin') }"
                active-class="active"
                class="pointer"
                data-cy="adminMenu"
              >
                <span slot="button-content" class="navbar-dropdown-menu">
                  <font-awesome-icon icon="users-cog" />
                  <span class="no-bold">Administration</span>
                </span>
                <b-dropdown-item to="/admin/user-management" active-class="active">
                  <font-awesome-icon icon="users" />
                  <span>User management</span>
                </b-dropdown-item>
                <b-dropdown-item to="/admin/metrics" active-class="active">
                  <font-awesome-icon icon="tachometer-alt" />
                  <span>Metrics</span>
                </b-dropdown-item>
                <b-dropdown-item to="/admin/health" active-class="active">
                  <font-awesome-icon icon="heart" />
                  <span>Health</span>
                </b-dropdown-item>
                <b-dropdown-item to="/admin/configuration" active-class="active">
                  <font-awesome-icon icon="cogs" />
                  <span>Configuration</span>
                </b-dropdown-item>
                <b-dropdown-item to="/admin/logs" active-class="active">
                  <font-awesome-icon icon="tasks" />
                  <span>Logs</span>
                </b-dropdown-item>
                <b-dropdown-item v-if="openAPIEnabled" to="/admin/docs" active-class="active">
                  <font-awesome-icon icon="book" />
                  <span>API</span>
                </b-dropdown-item>
                <b-dropdown-item v-if="!inProduction" href="./h2-console/" target="_tab">
                  <font-awesome-icon icon="database" />
                  <span>Database</span>
                </b-dropdown-item>
              </b-nav-item-dropdown>
              <b-nav-item-dropdown
                right
                href="javascript:void(0);"
                id="account-menu"
                :class="{ 'router-link-active': subIsActive('/account') }"
                active-class="active"
                class="pointer"
                data-cy="accountMenu"
              >
                <span slot="button-content" class="navbar-dropdown-menu">
                  <font-awesome-icon icon="user" />
                  <span class="no-bold"> Account </span>
                </span>
                <b-dropdown-item data-cy="settings" to="/account/settings" tag="b-dropdown-item" v-if="authenticated" active-class="active">
                  <font-awesome-icon icon="wrench" />
                  <span>Settings</span>
                </b-dropdown-item>
                <b-dropdown-item
                  data-cy="passwordItem"
                  to="/account/password"
                  tag="b-dropdown-item"
                  v-if="authenticated"
                  active-class="active"
                >
                  <font-awesome-icon icon="lock" />
                  <span>Password</span>
                </b-dropdown-item>
                <b-dropdown-item data-cy="logout" v-if="authenticated" v-on:click="logout()" id="logout" active-class="active">
                  <font-awesome-icon icon="sign-out-alt" />
                  <span>Sign out</span>
                </b-dropdown-item>
                <b-dropdown-item data-cy="login" v-if="!authenticated" v-on:click="openLogin()" id="login" active-class="active">
                  <font-awesome-icon icon="sign-in-alt" />
                  <span>Sign in</span>
                </b-dropdown-item>
                <b-dropdown-item
                  data-cy="register"
                  to="/register"
                  tag="b-dropdown-item"
                  id="register"
                  v-if="!authenticated"
                  active-class="active"
                >
                </b-dropdown-item>
              </b-nav-item-dropdown>
            </b-nav>
          </nav>
        </div>
      </template>
    </b-sidebar>
    <!--b-navbar data-cy="navbar" toggleable="md" type="light" class="bg-light">
      <b-navbar-brand class="logo" b-link to="/">
        <span class="logo-img"></span>
        <span class="navbar-title">posms</span> <span class="navbar-version">{{ version }}</span>
      </b-navbar-brand>
      <b-navbar-toggle
        right
        class="jh-navbar-toggler d-lg-none"
        href="javascript:void(0);"
        data-toggle="collapse"
        target="header-tabs"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <font-awesome-icon icon="bars" />
      </b-navbar-toggle>

      <b-collapse is-nav id="header-tabs">
        <b-navbar-nav class="ml-auto">
          <b-nav-item to="/" exact>
            <span>
              <font-awesome-icon icon="home" />
              <span>Home</span>
            </span>
          </b-nav-item>
          <b-nav-item-dropdown right id="entity-menu" v-if="authenticated" active-class="active" class="pointer" data-cy="entity">
            <span slot="button-content" class="navbar-dropdown-menu">
              <font-awesome-icon icon="th-list" />
              <span class="no-bold">Entities</span>
            </span>
            <entities-menu></entities-menu>
          </b-nav-item-dropdown>
          <b-nav-item-dropdown
            right
            id="admin-menu"
            v-if="hasAnyAuthority('ROLE_ADMIN') && authenticated"
            :class="{ 'router-link-active': subIsActive('/admin') }"
            active-class="active"
            class="pointer"
            data-cy="adminMenu"
          >
            <span slot="button-content" class="navbar-dropdown-menu">
              <font-awesome-icon icon="users-cog" />
              <span class="no-bold">Administration</span>
            </span>
            <b-dropdown-item to="/admin/user-management" active-class="active">
              <font-awesome-icon icon="users" />
              <span>User management</span>
            </b-dropdown-item>
            <b-dropdown-item to="/admin/metrics" active-class="active">
              <font-awesome-icon icon="tachometer-alt" />
              <span>Metrics</span>
            </b-dropdown-item>
            <b-dropdown-item to="/admin/health" active-class="active">
              <font-awesome-icon icon="heart" />
              <span>Health</span>
            </b-dropdown-item>
            <b-dropdown-item to="/admin/configuration" active-class="active">
              <font-awesome-icon icon="cogs" />
              <span>Configuration</span>
            </b-dropdown-item>
            <b-dropdown-item to="/admin/logs" active-class="active">
              <font-awesome-icon icon="tasks" />
              <span>Logs</span>
            </b-dropdown-item>
            <b-dropdown-item v-if="openAPIEnabled" to="/admin/docs" active-class="active">
              <font-awesome-icon icon="book" />
              <span>API</span>
            </b-dropdown-item>
            <b-dropdown-item v-if="!inProduction" href="./h2-console/" target="_tab">
              <font-awesome-icon icon="database" />
              <span>Database</span>
            </b-dropdown-item>
          </b-nav-item-dropdown>
          <b-nav-item-dropdown
            right
            href="javascript:void(0);"
            id="account-menu"
            :class="{ 'router-link-active': subIsActive('/account') }"
            active-class="active"
            class="pointer"
            data-cy="accountMenu"
          >
            <span slot="button-content" class="navbar-dropdown-menu">
              <font-awesome-icon icon="user" />
              <span class="no-bold"> Account </span>
            </span>
            <b-dropdown-item data-cy="settings" to="/account/settings" tag="b-dropdown-item" v-if="authenticated" active-class="active">
              <font-awesome-icon icon="wrench" />
              <span>Settings</span>
            </b-dropdown-item>
            <b-dropdown-item data-cy="passwordItem" to="/account/password" tag="b-dropdown-item" v-if="authenticated" active-class="active">
              <font-awesome-icon icon="lock" />
              <span>Password</span>
            </b-dropdown-item>
            <b-dropdown-item data-cy="logout" v-if="authenticated" v-on:click="logout()" id="logout" active-class="active">
              <font-awesome-icon icon="sign-out-alt" />
              <span>Sign out</span>
            </b-dropdown-item>
            <b-dropdown-item data-cy="login" v-if="!authenticated" v-on:click="openLogin()" id="login" active-class="active">
              <font-awesome-icon icon="sign-in-alt" />
              <span>Sign in</span>
            </b-dropdown-item>
            <b-dropdown-item
              data-cy="register"
              to="/register"
              tag="b-dropdown-item"
              id="register"
              v-if="!authenticated"
              active-class="active"
            >
            </b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar-->
  </b-navbar>
</template>

<script lang="ts" src="./client-navbar.component.ts"></script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* ==========================================================================
    Navbar
    ========================================================================== */
.navbar > button {
  border-color: rgba(0, 0, 0, 0);
}
.no-bg {
  border-color: inherit;
  background-color: inherit;
}
.no-bg:hover {
  border-color: inherit;
  background-color: inherit;
}
.no-bg:active {
  border-color: inherit;
  background-color: inherit;
}
.burger {
  font-size: 2.5rem;
}
a {
  color: inherit;
}
.navbar-version {
  font-size: 10px;
}

@media screen and (min-width: 768px) {
  .jh-navbar-toggler {
    display: none;
  }
}

@media screen and (min-width: 768px) and (max-width: 1150px) {
  span span {
    display: none;
  }
}

.navbar-title {
  display: inline-block;
  vertical-align: middle;
}

/* ==========================================================================
    Logo styles
    ========================================================================== */
.navbar-brand.logo {
  padding: 0px 20px;
}

.logo {
  padding-bottom: 20px;
}

.logo .logo-img {
  height: 45px;
  display: inline-block;
  vertical-align: middle;
  width: 70px;
}

.logo-img {
  height: 100%;
  background: url('../../../content/images/logo-jhipster.png') no-repeat center center;
  background-size: contain;
  width: 100%;
  filter: drop-shadow(0 0 0.05rem white);
}
</style>
