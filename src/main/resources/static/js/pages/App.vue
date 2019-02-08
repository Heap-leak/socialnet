<template>
    <div>
        <div v-if="!profile">Needed authorize through the
            <a href="/login">Google</a>
        </div>
        <div v-else>
            <div>
                {{profile.name}}&nbsp;<a href="/logout">Log Out</a>
            </div>
            <messages-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
    import MessagesList from "components/messages/MessagesList.vue"
    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: frontData.messages,
                profile: frontData.profile
            }
        }
    }
</script>

<style>

</style>