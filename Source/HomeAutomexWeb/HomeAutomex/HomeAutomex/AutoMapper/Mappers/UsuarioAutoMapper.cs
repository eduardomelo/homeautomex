using AutoMapper;
using HomeAutomex.Models;
using HomeAutomexLibrary.Entidade;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace HomeAutomex.AutoMapper.Mappers
{
    public class UsuarioAutoMapper : Profile
    {
        protected override void Configure()
        {
            Mapper.CreateMap<UsuarioModel, Usuario>();

            Mapper.CreateMap<Usuario, UsuarioModel>();
            base.Configure();
        }
    }
}