

var messageApi = Vue.resource("/message{/id}");

Vue.component('message-row',{
    props:['message'],
    template: '<div><i>({{message.id}})</i>{{message.message}}</div>'
});

Vue.component('messages-list', {
    props: ['messages'],
    template: '<div><message-row v-for="message in messages" :key="message.id" :message="message"/></div>',
    created: function(){
        messageApi.get().then(result =>
            result.json().then( data =>
                data.forEach(message => this.messages.push(this.message))
            )
        )
    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
        messages: []
    }
});