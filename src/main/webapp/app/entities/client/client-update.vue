<template>
  <div class="row justify-content-center">
    <div class="col-8">
      <form name="editForm" role="form" novalidate v-on:submit.prevent="save()">
        <h2 id="floPosmsApp.client.home.createOrEditLabel" data-cy="ClientCreateUpdateHeading">Create or edit a Client</h2>
        <div>
          <div class="form-group" v-if="client.id">
            <label for="id">ID</label>
            <input type="text" class="form-control" id="id" name="id" v-model="client.id" readonly />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-clientName">Client Name</label>
            <input
              type="text"
              class="form-control"
              name="clientName"
              id="client-clientName"
              data-cy="clientName"
              :class="{ valid: !$v.client.clientName.$invalid, invalid: $v.client.clientName.$invalid }"
              v-model="$v.client.clientName.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-clientCode">Client Code</label>
            <input
              type="text"
              class="form-control"
              name="clientCode"
              id="client-clientCode"
              data-cy="clientCode"
              :class="{ valid: !$v.client.clientCode.$invalid, invalid: $v.client.clientCode.$invalid }"
              v-model="$v.client.clientCode.$model"
            />
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-clientType">Client Type</label>
            <select
              class="form-control"
              name="clientType"
              :class="{ valid: !$v.client.clientType.$invalid, invalid: $v.client.clientType.$invalid }"
              v-model="$v.client.clientType.$model"
              id="client-clientType"
              data-cy="clientType"
            >
              <option v-for="clientType in clientTypeValues" :key="clientType" v-bind:value="clientType">{{ clientType }}</option>
            </select>
          </div>
          <div class="form-group">
            <label class="form-control-label" for="client-validityDate">Validity Date</label>
            <div class="d-flex">
              <input
                id="client-validityDate"
                data-cy="validityDate"
                type="datetime-local"
                class="form-control"
                name="validityDate"
                :class="{ valid: !$v.client.validityDate.$invalid, invalid: $v.client.validityDate.$invalid }"
                :value="convertDateTimeFromServer($v.client.validityDate.$model)"
                @change="updateZonedDateTimeField('validityDate', $event)"
              />
            </div>
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
            :disabled="$v.client.$invalid || isSaving"
            class="btn btn-primary"
          >
            <font-awesome-icon icon="save"></font-awesome-icon>&nbsp;<span>Save</span>
          </button>
        </div>
      </form>
    </div>
  </div>
</template>
<script lang="ts" src="./client-update.component.ts"></script>
