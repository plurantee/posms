<template>
  <div>
    <div class="alert alert-success" v-if="authenticated">
      <span v-if="username">You are logged in as user "{{ username }}"</span>
      <div></div>
    </div>
    <div v-if="authenticated">
      <b-nav tabs>
        <b-nav-item @click="switchNav('lazada')" :active="shopNav == 'lazada'">Lazada</b-nav-item>
        <b-nav-item @click="switchNav('shopee')" :active="shopNav == 'shopee'">Shopee</b-nav-item>
      </b-nav>

      <client-lazada-order v-if="shopNav == 'lazada'"></client-lazada-order>
      <client-shopee-order v-if="shopNav == 'shopee'"></client-shopee-order>
    </div>
    <div class="alert alert-warning" v-if="!authenticated">
      <span>If you want to </span>
      <a class="alert-link" v-on:click="openLogin()">sign in</a
      ><span
        >, you can try the default accounts:<br />- Administrator (login="admin" and password="admin") <br />- User (login="user" and
        password="user").</span
      >
    </div>
    <div class="alert alert-warning" v-if="!authenticated">
      <span>You don't have an account yet?</span>&nbsp;
      <router-link class="alert-link" to="/register">Register a new account</router-link>
    </div>
  </div>
</template>

<script lang="ts" src="./client-home.component.ts"></script>
