define [
  'lib/backbone'
  'lib/jquery'
  'lib/handlebars'
  'app/controller/tournamentController'
  'text!html/teamTemplate.html'
], (Backbone, $, handlebars, tournamentController, strTeamTemplate) ->
  Backbone.View.extend(
    initialize: (options) ->
      @model.on('change', @update, @)
      @teamHB = handlebars.compile(strTeamTemplate)
      @render()

    render: ->
      @$el = $(@teamHB(@model.toJSON()))

      $(@$el.find('.team')).draggable(
        helper: 'clone'
        opacity: 0.6
        start:(event, ui) =>
          @trigger('drag',@,ui)
        stop:(event, ui) =>
          @trigger('dragStop',@,ui)

      )

      @$el.droppable(
        tolerance: 'pointer'
        drop: (event,ui) =>
          @trigger('drop',@,ui)
        over: (event, ui) ->
#              $(event.target).addClass "team-droppable"
        out:  (event, ui) ->
#              $(event.target).removeClass "team-droppable"
      )

    update: ->
      @$el.replaceWith(@teamHB(@model.toJSON()))


    events:
      "click .detail": "click"

    click: (e) ->
      console.log(@model.get('name'))

    showDropZone: ->
      @$el.addClass "highlight-game-drop"

    hideDropZone: ->
      @$el.removeClass "highlight-game-drop"

  )