<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.userInfo.home.createOrEditLabel" data-cy="UserInfoCreateUpdateHeading">Create or edit a UserInfo</h2>
        <div>
          <div class="form-group" v-if="userInfo.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="userInfo.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="user-info-user">User</label>
            <select class="form-control" id="user-info-user" data-cy="user" name="user" v-model="userInfo.user">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="userInfo.user && userOption.id === userInfo.user.id ? userInfo.user : userOption"
                v-for="userOption in users"
                :key="userOption.id"
              >
                {{ userOption.login }}
              </option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="user-info-clientCode">Client Code</label>
            <select class="form-control" id="user-info-clientCode" data-cy="clientCode" name="clientCode" v-model="userInfo.clientCode">
              <option v-bind:value="null"></option>
              <option
                v-bind:value="userInfo.clientCode && clientOption.id === userInfo.clientCode.id ? userInfo.clientCode : clientOption"
                v-for="clientOption in clients"
                :key="clientOption.id"
              >
                {{ clientOption.clientCode }}
              </option>
            </select>
          </div>
        </div>
        <div>
          <button type="button" id="cancel-save" data-cy="entityCreateCancelButton" class="btn btn-secondary" v-on:click="previousState()">
            <font-awesome-icon icon="ban"></font-awesome-icon>&nbsp;<span>Cancel</span>
          </button>
          <button
            type="submit"
            id="save-entity"
            data-cy="entityCreateSaveButton"
            :disabled="$v.userInfo.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./user-info-update.component.ts"></script>
